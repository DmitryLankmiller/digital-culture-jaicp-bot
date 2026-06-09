theme: /

    state: Start
        q!: $regex</start>
        a: Напишите: дата или день недели.

    state: Date
        q!: $regex<.*(дата|число|сегодня|какое сегодня число|текущая дата|current date|date).*>
        script:
            var now = new Date();
            var day = now.getUTCDate();
            var month = now.getUTCMonth() + 1;
            var year = now.getUTCFullYear();
            var dd = day < 10 ? "0" + day : "" + day;
            var mm = month < 10 ? "0" + month : "" + month;
            $session.dateAnswer = dd + "." + mm + "." + year;
        a: {{$session.dateAnswer}}

    state: Week Day
        q!: $regex<.*(день недели|какой день недели|сегодня день|weekday|week day|day of week).*>
        script:
            var now = new Date();
            var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
            $session.weekDayAnswer = days[now.getUTCDay()];
        a: {{$session.weekDayAnswer}}

    state: NoMatch
        event!: noMatch
        a: Я не понял запрос.
