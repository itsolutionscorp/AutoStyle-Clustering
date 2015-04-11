# Python "Leap" exercise. 
# Iteration 1
def is_leap_year(year):
    if year % 4 == 0 and not year % 100 == 0 or year % 400 == 0:
        return True
