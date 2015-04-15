__author__ = 'nebur1989'

from string import Template

verse_template = Template('On the $day day of Christmas my true love gave to me, $what.\n')

song = {
    1: ('first', 'a Partridge in a Pear Tree'),
    2: ('second', 'two Turtle Doves'),
    3: ('third', 'three French Hens'),
    4: ('fourth', 'four Calling Birds'),
    5: ('fifth', 'five Gold Rings'),
    6: ('sixth', 'six Geese-a-Laying'),
    7: ('seventh', 'seven Swans-a-Swimming'),
    8: ('eighth', 'eight Maids-a-Milking'),
    9: ('ninth', 'nine Ladies Dancing'),
    10: ('tenth', 'ten Lords-a-Leaping'),
    11: ('eleventh', 'eleven Pipers Piping'),
    12: ('twelfth', 'twelve Drummers Drumming')
}


def sing():
    return verses(1, 12)


def verse(num):
    day, what = song[num]
    what_str = what
    for n in reversed(range(1, num)):
        _, what = song[n]
        sep = ", "
        if n == 1:
            sep = ", and "
        what_str += sep + what
    return verse_template.substitute(day=day, what=what_str)


def verses(init, end):
    verses_str = ""
    for num in range(init, end + 1):
        verses_str += verse(num) + '\n'
    return verses_str
