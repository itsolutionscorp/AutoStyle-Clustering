from calendar import monthcalendar
from datetime import date


def meetup_day(year, month, weekday, posi):
    # values will mean the position of the day in a calendar list.
    wdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2,
             'Thursday': 3, 'Friday': 4, 'Saturday': 5,
             'Sunday': 6}

    posidict = {'1st': 1, '2nd': 2, '3rd': 3,
                '4th': 4, 'last': 5}
    M = monthcalendar(year, month)
    nday = wdays[weekday]  # take the day position inside the list.

    if posi == 'teenth':
        for i in range(len(M)):
            if M[i][nday] in [13, 14, 15, 16, 17, 18, 19]:
                day = M[i][nday]
                return date(year, month, day)
######
    elif posi == 'last':
        x = 0
        dia = wdays[weekday]

        for W in range(len(M)):
            nweek = posidict[posi]

            if M[W][dia] != 0:
                x += 1
                day = M[W][dia]

        return date(year, month, day)

    elif posi in ['1st', '2nd', '3rd', '4th', '5th', '6th', '7th']:
        x = 0
        dia = wdays[weekday]

        for W in range(len(M)):
            nweek = posidict[posi]

            if M[W][dia] != 0:
                x += 1
                if x == nweek:
                    day = M[W][dia]

                    return date(year, month, day)
