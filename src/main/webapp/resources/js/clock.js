function clockTimer() {
    const date = new Date();
    const time = [date.getHours(), date.getMinutes(), date.getSeconds()];
    const dayOfWeek = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const days = date.getDay();
    if(time[0] < 10){time[0] = "0"+ time[0];}
    if(time[1] < 10){time[1] = "0"+ time[1];}
    if(time[2] < 10){time[2] = "0"+ time[2];}
    const current_time = [time[0], time[1], time[2]].join(':');
    const clock = document.getElementById("clock");
    const day = document.getElementById("dayOfWeek");
    clock.innerHTML = current_time;
    day.innerHTML = dayOfWeek[days];
    setTimeout("clockTimer()", 9000);
}
