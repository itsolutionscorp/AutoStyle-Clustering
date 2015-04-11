GIFTS = [None,
        'a Partridge in a Pear Tree',
        'two Turtle Doves',
        'three French Hens',
        'four Calling Birds',
        'five Gold Rings',
        'six Geese-a-Laying',
        'seven Swans-a-Swimming',
        'eight Maids-a-Milking',
        'nine Ladies Dancing',
        'ten Lords-a-Leaping',
        'eleven Pipers Piping',
        'twelve Drummers Drumming']

DAYS = [None,
        'first', 'second', 'third', 'fourth',
        'fifth', 'sixth', 'seventh', 'eighth',
        'ninth', 'tenth', 'eleventh', 'twelfth']

def verse(n):
    gifts = ''
    if n == 1:
        gifts = GIFTS[1]
    else:
        for i in reversed(range(2, n + 1)):
            gifts += GIFTS[i] + ', '
        gifts += 'and ' + GIFTS[1]

    return 'On the {} day of Christmas my true love gave to me, {}.\n'.format(
            DAYS[n], gifts);

def verses(n, m):
    song = ''
    for v in range(n, m + 1):
        song += verse(v) + '\n'
    return song

def sing():
    return verses(1, 12)
