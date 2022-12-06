function processFieldSelection(field, value, fieldName) {
    if (field.value === value) {
        field.value = "";
        document.getElementById(fieldName + value).classList.remove('selected');
    } else {
        if (field.value !== "") {
            document.getElementById(fieldName + field.value).classList.remove('selected');
        }
        field.value = value;
        document.getElementById(fieldName + value).classList.add('selected');
    }
}
