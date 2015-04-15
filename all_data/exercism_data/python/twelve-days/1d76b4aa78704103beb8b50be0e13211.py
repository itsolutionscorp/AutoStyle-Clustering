days = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']
gifts = ['twelve Drummers Drumming', 'eleven Pipers Piping', 'ten Lords-a-Leaping', 'nine Ladies Dancing', 'eight Maids-a-Milking',
         'seven Swans-a-Swimming', 'six Geese-a-Laying', 'five Gold Rings', 'four Calling Birds', 'three French Hens',
         'two Turtle Doves', 'a Partridge in a Pear Tree']


def verse(n):
    result = "On the %s day of Christmas my true love gave to me, " % (days[n-1])
    result += ', '.join([gifts[d] for d in range(12 - n, 11) ])
    if n > 1:
        result += ", and "
    result += gifts[11] + ".\n"
    return result

def verses(start, end):
    result = ""
    for i in range(start, end + 1):
        result += verse(i) + "\n"
    return result

def sing():
    return verses(1, 12)
