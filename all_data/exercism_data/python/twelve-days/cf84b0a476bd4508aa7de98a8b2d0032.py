gifts = [
    'Partridge in a Pear Tree',
    'Turtle Doves',
    'French Hens',
    'Calling Birds',
    'Gold Rings',
    'Geese-a-Laying',
    'Swans-a-Swimming',
    'Maids-a-Milking',
    'Ladies Dancing',
    'Lords-a-Leaping',
    'Pipers Piping',
    'Drummers Drumming',
]

cardinals = ['a', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight',
             'nine', 'ten', 'eleven', 'twelve']
ordinals = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh',
            'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']

base = 'On the {} day of Christmas my true love gave to me, {}.\n'
sing = lambda: verses(1, 12)
verses = lambda s, e: '\n'.join(verse(i) for i in range(s, e + 1)) + '\n'
_make = lambda i: '{} {}'.format(cardinals[i - 1], gifts[i - 1])


def verse(i):
    if i == 1:
        g = _make(1)
    else:
        g = '{}, and {}'.format(
            ', '.join(_make(i) for i in range(i, 1, -1)),
            _make(1)
        )
    return base.format(ordinals[i - 1], g)
