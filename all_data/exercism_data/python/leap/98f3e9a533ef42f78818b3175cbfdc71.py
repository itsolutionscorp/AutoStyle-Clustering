def is_leap_year(year):
    # Check if year is evenly divisible by 4
    if (year % 4) == 0:
        # Check if year is evenly divisible by 100
        if (year % 100) == 0:
            # If year is evenly divisible by 100 & 400 it is leap year
            if (year % 400) == 0:
                return True
            # If only divisible by 100 then it is not leap year
            return False
        return True
    else:
        return False
    
    
