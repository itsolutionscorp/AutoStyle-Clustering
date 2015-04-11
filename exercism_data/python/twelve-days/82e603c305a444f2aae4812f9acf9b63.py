gifts = ['a Partridge in a Pear Tree', 'two Turtle Doves', 'three French Hens', 
         'four Calling Birds', 'five Gold Rings', 'six Geese-a-Laying', 
         'seven Swans-a-Swimming', 'eight Maids-a-Milking', 'nine Ladies Dancing', 
         'ten Lords-a-Leaping', 'eleven Pipers Piping', 'twelve Drummers Drumming']

days = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 
        'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']

def sing():
    return verses(1, 12)

def verse(num):
    if num < 1 or num > 12:
        raise ValueError('Only twelve days of Christmas.')
    day = days[num - 1]
    vstring = 'On the %s day of Christmas my true love gave to me, ' % day
    for i in range(num - 1, -1, -1):
        vstring += gifts[i]
        if i > 1:
            vstring += ', '
        elif i == 1:
            vstring += ', and '
    vstring += '.\n'
    return vstring

def verses(first, last):
    vstring = ''
    for i in range(first, last + 1):
        vstring += verse(i) + '\n'
    return vstring
