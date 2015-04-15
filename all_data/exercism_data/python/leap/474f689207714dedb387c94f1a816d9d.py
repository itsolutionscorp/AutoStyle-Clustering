def is_leap_year(year):
    reply = False

    try:
        int(year)
    except ValueError:
        return False

    if year % 4 == 0:
        reply = True
        if year % 100 == 0:
            reply = False
            if year % 400 == 0:
                reply = True

    return reply
