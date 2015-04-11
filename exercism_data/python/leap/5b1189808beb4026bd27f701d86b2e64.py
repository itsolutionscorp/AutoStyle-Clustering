def is_leap_year(year):
    response = False

    if year % 4 == 0:
        response =  True

    if year % 100 == 0 and year % 400 != 0:
        response = False

    return response
