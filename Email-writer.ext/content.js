
console.log("content script loaded");

function inject() {
  document.querySelectorAll(".aDh").forEach(toolbar => {
    if (toolbar.querySelector(".email-writer-btn")) return;

    const sendBtn = toolbar.querySelector(
      'div[role="button"][data-tooltip^="Send"], div[role="button"][aria-label^="Send"]'
    );
    if (!sendBtn) return;

    const writerBtn = document.createElement("div");
    writerBtn.className = sendBtn.className + " email-writer-btn";
    writerBtn.classList.remove("T-I-atl");

    writerBtn.textContent = "AI Reply";
    writerBtn.style.marginLeft = "8px";
    writerBtn.style.color= "white";
    writerBtn.style.borderRadius = "999px";
    writerBtn.style.padding = "0 16px";
    writerBtn.style.display = "inline-flex";
    writerBtn.style.alignItems = "center";
    writerBtn.style.justifyContent = "center";
    writerBtn.style.height = sendBtn.offsetHeight + "px";

    writerBtn.addEventListener("click", () => handleGenerate(writerBtn));

    sendBtn.nextElementSibling.after(writerBtn);

    console.log("button injected");
  });
}

function getEmailText() {
  const emailBody = document.querySelector("div.a3s.aiL");
  return emailBody ? emailBody.innerText.trim() : null;
}

function getEditor() {
  return document.querySelector(
    'div[contenteditable="true"][role="textbox"]'
  );
}

async function handleGenerate(button) {
  const emailText = getEmailText();
  const editor = getEditor();

  if (!emailText || !editor) {
    alert("Open an email and click Reply / Compose first");
    return;
  }

  button.textContent = "Generating...";
  button.style.pointerEvents = "none";

  try {
    const res = await fetch("http://localhost:8080/api/email/generate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        emailContent: emailText,
        tone: "professional"
      })
    });

    const data = await res.text();

    insertText(editor, data);
  } catch (e) {
    console.error(e);
    alert("Failed to generate response");
  } finally {
    button.textContent = "Generate";
    button.style.pointerEvents = "auto";
  }
}

function insertText(editor, text) {
  editor.focus();
  document.execCommand("insertText", false, text);
}

inject();

new MutationObserver(inject).observe(document.body, {
  childList: true,
  subtree: true
});
