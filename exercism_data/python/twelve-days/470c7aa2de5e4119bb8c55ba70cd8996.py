days = [
    ('first', 'a Partridge in a Pear Tree'),
    ('second', 'two Turtle Doves'),
    ('third', 'three French Hens'),
    ('fourth', 'four Calling Birds'),
    ('fifth', 'five Gold Rings'),
    ('sixth', 'six Geese-a-Laying'),
    ('seventh', 'seven Swans-a-Swimming'),
    ('eighth', 'eight Maids-a-Milking'),
    ('ninth', 'nine Ladies Dancing'),
    ('tenth', 'ten Lords-a-Leaping'),
    ('eleventh', 'eleven Pipers Piping'),
    ('twelfth', 'twelve Drummers Drumming')
]


def sing():
    return verses(1, 12)


def verses(start, end):
    song = ''
    for number in range(start, end+1):
        song += verse(number) + '\n'
    return song


def verse(number):
    th_day = th_day_of_the_week(number)
    song = 'On the ' + th_day + ' day of Christmas my true love gave to me, '
    if number > 1:
        for day in range(1, number)[::-1]:
            song += phrase(day+1) + ', '
        song += 'and ' + phrase(1)
    else:
        song += phrase(1)
    song += '.\n'
    return song


def th_day_of_the_week(day):
    return days[day-1][0]


def phrase(day):
    return days[day-1][1]
