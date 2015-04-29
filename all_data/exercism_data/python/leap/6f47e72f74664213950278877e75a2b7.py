def is_leap_year(year):
    divBy4 = year % 4
    divBy100 = year % 100
    divBy400 = year % 400
    #converting mod values to boolean in lieau of checking up on divisibility
    #I am somewhat skeptic on using the same variables. Should i use different ones
    #so as not to confuse integer with booleans or is that irrelevant in python?
    if divBy4 == 0:
        divBy4 = True
    else:
        divBy4 = False
    if divBy100 == 0:
        divBy100 = True
    else:
        divBy100 = False
    if divBy400 == 0:
        divBy400 = True
    else:
        divBy400 = False
    if divBy4 and divBy400:
        return True
    elif divBy4 and not divBy100:
        return True
    else:
        return False
