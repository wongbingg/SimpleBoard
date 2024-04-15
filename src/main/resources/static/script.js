// script.js
const postForm = document.getElementById('postForm');
const postsContainer = document.getElementById('posts');

postForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const author = document.getElementById('author').value;
    const content = document.getElementById('content').value;

    const response = await fetch('/api/posts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            author: author,
            content: content
        })
    });

    if (response.ok) {
        alert('Post created successfully!');
        document.getElementById('author').value = '';
        document.getElementById('content').value = '';
        fetchPosts();
    } else {
        alert('Failed to create post');
    }
});

async function fetchPosts() {
    const response = await fetch('/api/posts');
    const posts = await response.json();
    displayPosts(posts);
}

function displayPosts(posts) {
    postsContainer.innerHTML = '';
    posts.forEach(post => {
        const postElement = document.createElement('div');
        postElement.innerHTML = `<strong>${post.author}</strong>: ${post.content}`;
        postsContainer.appendChild(postElement);
    });
}

fetchPosts();