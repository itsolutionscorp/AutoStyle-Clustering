day = ['', 'first', 'second', 'third', 'fourth', 'fifth', 'sixth',
       'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']

lyric = ['two Turtle Doves, ',
         'three French Hens, ',
         'four Calling Birds, ',
         'five Gold Rings, ',
         'six Geese-a-Laying, ',
         'seven Swans-a-Swimming, ',
         'eight Maids-a-Milking, ',
         'nine Ladies Dancing, ',
         'ten Lords-a-Leaping, ',
         'eleven Pipers Piping, ',
         'twelve Drummers Drumming, ']

def verse(v):
    ret = 'On the %s day of Christmas my true love gave to me, ' % day[v]
    for i in range(v - 1)[::-1]:
        ret += lyric[i]

    if v > 1:
        ret += 'and '
    ret += 'a Partridge in a Pear Tree.\n'

    return ret

def verses(b, e):
    ret = ''

    for v in range(b, e + 1):
        ret += verse(v) + '\n'

    return ret

def sing():
    return verses(1, 12)
