#!/usr/bin/python3


LYRICS = [
    None,
    ['first', 'and a Partridge in a Pear Tree.\n'],
    ['second', 'two Turtle Doves'],
    ['third', 'three French Hens'],
    ['fourth', 'four Calling Birds'],
    ['fifth', 'five Gold Rings'],
    ['sixth', 'six Geese-a-Laying'],
    ['seventh', 'seven Swans-a-Swimming'],
    ['eighth', 'eight Maids-a-Milking'],
    ['ninth', 'nine Ladies Dancing'],
    ['tenth', 'ten Lords-a-Leaping'],
    ['eleventh', 'eleven Pipers Piping'],
    ['twelfth', 'twelve Drummers Drumming'],
]


def verse(day_num):
    """
    Returns one verse from 'The Twelve Days of Christmas'
    """
    assert day_num in range(1, 13)

    first_phrase_templ = 'On the {} day of Christmas my true love gave to me'
    first_phrase = first_phrase_templ.format(LYRICS[day_num][0])

    if day_num == 1:
        return first_phrase + ', ' + LYRICS[day_num][1][4:]

    next_phrases = [LYRICS[day][1] for day in range(day_num, 0, -1)]
    return ', '.join([first_phrase] + next_phrases)


def verses(start, end):
    """
    Returns verses from @start to @end of 'The Twelve Days of Christmas'
    """
    return '\n'.join(verse(day_num) for day_num in range(start, end+1)) + '\n'


def sing():
    """
    Returns all song 'The Twelve Days of Christmas'
    """
    return verses(1, 12)


if __name__ == '__main__':
    pass
