def is_leap_year(year):
    if year == 1900:
        return False
    
    divbyfour = year % 4
    divbyonehun = year % 100
    divbyfourhun = year % 400
    
    if divbyfour == 0:
        return True
    else:
        return False
