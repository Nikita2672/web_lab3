function cleanInput() {
    if (FIELD_X.value !== "" && FIELD_X.value !== undefined) {
        document.getElementById('j_idt:x' + FIELD_X.value).classList.remove('selected');
        FIELD_X.value = "";
    }
    FIELD_Y.value = "";
    if (FIELD_R.value !== "" && FIELD_R.value !== undefined) {
        document.getElementById('j_idt:r' + FIELD_R.value).classList.remove('selected');
        FIELD_R.value = "";
    }
}
