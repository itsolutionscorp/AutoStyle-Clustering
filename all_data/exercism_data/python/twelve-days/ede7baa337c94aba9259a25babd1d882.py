_gifts = [
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

_days = [
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


def verse(day):
    pre_string = "On the {} day of Christmas my true love gave to me, "
    pre = pre_string.format(_days[day-1])

    # comma-separated list of gifts from today's down to doves, inclusive
    todays_gifts = ', '.join(_gifts[day-1:0:-1])

    # add conjunction if there are many gifts
    if day > 1:
        todays_gifts += ', and '
    # a partridge in a pear tree!
    todays_gifts += _gifts[0]

    post = '.\n'
    return pre + todays_gifts + post


def verses(start, end):
    return "\n".join(verse(n) for n in range(start, end + 1)) + "\n"


def sing():
    return verses(1, 12)
