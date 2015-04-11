def is_factor(big, small):
    return big%small==0

def is_leap_year(year):
    return (is_factor(year,400) or
            is_factor(year,4) and not(is_factor(year,100))
           )
