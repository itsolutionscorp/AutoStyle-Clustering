# function for verifying leap years
def is_leap_year(yearInt):
    #check if divisible by 4
    if yearInt % 4 == 0:
        #check if also divisible by 100
        if yearInt % 100 == 0:
            #check if divisible by 100 and 400 then True
            # divisible by 100 not by 400 then False
            return yearInt % 400 == 0

        #divisible by 4 not by 100 then True
        return True

    # not divisible by 4 then False
    return False
