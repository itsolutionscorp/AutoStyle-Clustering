PREAMBLE_TEMPLATE = 'On the %s day of Christmas my true love gave to me, '

ORDINALS = [
    'first',
    'second',
    'third',
    'fourth',
    'fifth',
    'sixth',
    'seventh',
    'eighth',
    'ninth',
    'tenth',
    'eleventh',
    'twelfth'
]

GIFTS = [
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
    'twelve Drummers Drumming',
]


def _preamble(n):
    return PREAMBLE_TEMPLATE % ORDINALS[n - 1]


def _body(n):
    if n == 1:
        return GIFTS[0]
    gifts = GIFTS[n - 1::-1]
    return '%s, and %s' % (', '.join(gifts[:-1]), gifts[-1])


def verse(n):
    return '%s%s.\n' % (_preamble(n), _body(n))


def verses(first, last):
    return '\n'.join(verse(i) for i in range(first, last + 1)) + '\n'


def sing():
    return verses(1, 12)
