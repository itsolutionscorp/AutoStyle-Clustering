#kling 11/13/2014
#Exercism Exercise 2 - Leap

def is_leap_year(number):
    if number % 4 == 0 and number % 100 != 0:
        return True
    elif number % 400 == 0:
        return True
    else: 
        return False
