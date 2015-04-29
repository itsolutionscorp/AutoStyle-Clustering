def is_leap_year(userInput):
    if (userInput % 400 == 0):
        return True
    if (userInput % 100 == 0):
        return False
    if (userInput % 4 == 0):
        return True
    else:
        return False
