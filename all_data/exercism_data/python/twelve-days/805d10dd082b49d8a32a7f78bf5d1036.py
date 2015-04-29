DAYS = [
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
    'twelfth',
]

THINGS = [
    'twelve Drummers Drumming',
    'eleven Pipers Piping',
    'ten Lords-a-Leaping',
    'nine Ladies Dancing',
    'eight Maids-a-Milking',
    'seven Swans-a-Swimming',
    'six Geese-a-Laying',
    'five Gold Rings',
    'four Calling Birds',
    'three French Hens',
    'two Turtle Doves',
    'a Partridge in a Pear Tree',
]


def sing():
    return verses(1, 12)


def verse(n):
    day = 'On the {} day of Christmas my true love gave to me,'.format(
        DAYS[n - 1])
    things = THINGS[-n:]
    if n > 1:
        things[-1] = 'and {}'.format(things[-1])
    return '{} {}.\n'.format(day, ', '.join(things))


def verses(start, end):
    result = []
    for i in range(start, end + 1):
        result.append(verse(i))
    if end - start > 1:
        return '\n'.join(result) + '\n'
    else:
        return '\n'.join(result)
