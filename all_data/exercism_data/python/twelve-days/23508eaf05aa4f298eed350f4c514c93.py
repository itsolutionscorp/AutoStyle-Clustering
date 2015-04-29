VERSE_STR = 'On the {ordinal} day of Christmas my true love gave to me, {items}.\n'

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
    'twelfth',
]
ITEMS = [
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


def comma_list(strings):
    strings = list(strings)
    return ', '.join('and ' + string
                     if len(strings) > 1 and string is strings[-1]
                     else string
                     for string in strings)


def verse(n):
    return VERSE_STR.format(ordinal=ORDINALS[n-1],
                            items=comma_list(reversed(ITEMS[:n])))


def verses(start, end):
    return '\n'.join(verse(n) for n in range(start, end+1)) + '\n'


def sing():
    return verses(1, 12)
