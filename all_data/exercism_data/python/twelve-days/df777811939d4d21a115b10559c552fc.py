def verse(v):
    day = ['', 'first', 'second', 'third', 'fourth', 'fifth', 'sixth',
           'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']

    lyric = ['twelve Drummers Drumming, ', 'eleven Pipers Piping, ',
             'ten Lords-a-Leaping, ', 'nine Ladies Dancing, ',
             'eight Maids-a-Milking, ', 'seven Swans-a-Swimming, ',
             'six Geese-a-Laying, ', 'five Gold Rings, ',
             'four Calling Birds, ', 'three French Hens, ',
             'two Turtle Doves, and ']

    ret = 'On the %s day of Christmas my true love gave to me, ' % day[v]
    ret += ''.join(lyric[12 - v:])
    ret += 'a Partridge in a Pear Tree.\n'

    return ret

def verses(b, e):
    ret = ''

    for v in range(b, e + 1):
        ret += verse(v) + '\n'

    return ret

def sing():
    return verses(1, 12)
