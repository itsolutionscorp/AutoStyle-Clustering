# Leap year implementation

# Last iteration of the code should be the fastest
# 3 out of 4 years are not leap years and execution
# will be limited to first conditional

def is_leap_year(year):
    if (year % 4) != 0:
        return False
    else:
        if (year % 100) == 0:
            if (year % 400) == 0:
                return True
            return False
        return True

def is_leap_year1(year):
    lyear = False
    if (year % 4) == 0:
        lyear = True
        if (year % 100) == 0:
           lyear = False
           if (year % 400) == 0:
               lyear = True
    return lyear

def is_leap_year2(year):
    if (year % 400) == 0:
        return True
    elif (year % 100) == 0:
        return False
    elif (year % 4) == 0:
        return True
    return False

def is_leap_year3(year):
    if (year % 4) == 0 and ((year % 100) != 0 or (year % 400) == 0):
        return True
    return False
