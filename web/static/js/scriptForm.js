const inputLinkimg = document.getElementById('linkimg');
const img = document.getElementById('img');

inputLinkimg.addEventListener("input", function () {
    console.log(inputLinkimg.value);
    img.style.display = "";
    img.src = inputLinkimg.value;
});