(function() {
  const root = document.getElementById('app');

  function el(tag, attrs, children) {
    const node = document.createElement(tag);
    if (attrs) Object.keys(attrs).forEach(k => {
      if (k === 'class') node.className = attrs[k]; else node.setAttribute(k, attrs[k]);
    });
    (children || []).forEach(c => {
      if (typeof c === 'string') node.appendChild(document.createTextNode(c));
      else node.appendChild(c);
    });
    return node;
  }

  function profileCard(p) {
    const avatar = el('div', { class: 'avatar mb-3' });
    const name = el('h3', { class: 'card-title' }, [p.name || 'Your Name']);
    const headline = el('p', { class: 'text-muted' }, [p.headline || '']);
    const location = el('p', null, [p.location || '']);

    const contact = el('div', { class: 'card mt-3' }, [
      el('div', { class: 'card-body' }, [
        el('h5', null, ['Contact']),
        el('p', null, [ el('a', { href: 'mailto:' + (p.email || '') }, [p.email || '']) ])
      ])
    ]);

    return el('div', { class: 'col-md-4' }, [
      el('div', { class: 'card mb-4' }, [el('div', { class: 'card-body text-center' }, [avatar, name, headline, location])]),
      contact
    ]);
  }

  function rightColumn(p) {
    const about = el('section', { id: 'about', class: 'mb-4' }, [el('h4', null, ['About']), el('p', null, [p.summary || ''])]);

    const expList = (p.experiences || []).map(exp => el('li', { class: 'mb-3' }, [
      el('h6', null, [exp.title || '']),
      el('div', { class: 'text-muted' }, [ (exp.company || '') + (exp.period ? ' • ' + exp.period : '') ]),
      el('p', null, [exp.description || ''])
    ]));
    const experience = el('section', { id: 'experience', class: 'mb-4' }, [el('h4', null, ['Experience']), el('ul', { class: 'list-unstyled' }, expList)]);

    const projectsList = (p.projects || []).map(pr => el('div', { class: 'card mb-3' }, [el('div', { class: 'card-body' }, [el('h6', { class: 'card-title' }, [el('a', { href: pr.url || '#' }, [pr.name || ''])]), el('p', null, [pr.description || ''])]) ]));
    const projects = el('section', { id: 'projects', class: 'mb-4' }, [el('h4', null, ['Projects']), ...projectsList]);

    const skillsBadges = (p.skills || []).map(s => el('span', { class: 'badge bg-secondary me-1' }, [s.name || '']));
    const skills = el('section', { id: 'skills', class: 'mb-4' }, [el('h4', null, ['Skills']), el('div', null, skillsBadges)]);

    return el('div', { class: 'col-md-8' }, [about, experience, projects, skills]);
  }

  function renderProfile(p) {
    root.innerHTML = '';
    const row = el('div', { class: 'row gx-4' }, [ profileCard(p), rightColumn(p) ]);
    root.appendChild(row);
  }

  function showError(msg) {
    root.innerHTML = '';
    root.appendChild(el('div', { class: 'alert alert-danger' }, [msg]));
  }

  fetch('/api/profile')
    .then(res => {
      if (!res.ok) throw new Error('Network response was not ok');
      return res.json();
    })
    .then(data => renderProfile(data))
    .catch(err => showError('Failed to load profile: ' + err.message));
})();
