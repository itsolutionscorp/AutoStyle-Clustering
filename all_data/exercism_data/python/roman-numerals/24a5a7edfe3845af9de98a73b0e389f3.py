#roman.py
# Check out the history of rome podcast, it's pretty amazing

#Options:
#1- Create a dictionary of key values and build our roman numeral from iteration... or recursion
#2- Create a translation somehow with regular expressions and try to be very pythonic
#Lets keep it simple stupid... like super simple


def numeral(arabic):
    roman = ''
    codex = {1000: 'M', 900: 'CM', 500: 'D', 400: 'CD', 100: 'C', 90: 'XC', 50: 'L',
             40: 'XL', 10: 'X', 9: 'IX', 5: 'V', 4: 'IV', 1: 'I'}

    keys = sorted(codex.keys())
    keys.reverse()# list methods return None so you have to break it up
    while arabic > 0:
        for c in keys:
            while arabic >= c:
                roman += codex[c]
                arabic -= c

    return roman
