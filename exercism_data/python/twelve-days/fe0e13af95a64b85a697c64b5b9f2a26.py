GIFTS = (('first', 'a Partridge in a Pear Tree'),
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
         ('twelfth', 'twelve Drummers Drumming'))


def verse(number):
    out = 'On the {0} day of Christmas my true love gave to me, {1}' \
            .format(*GIFTS[number - 1])

    if number > 2:
        out += ', ' + ', '.join([GIFTS[n][1] for n in range(number - 2, 0, -1)])
        out = out.strip() + ', and ' + GIFTS[0][1]
    elif number > 1:
        out += ', and ' + GIFTS[0][1]

    return out + '.\n'


def verses(start, end):
    return '\n'.join(verse(n) for n in range(start, end + 1)) + '\n'


def sing():
    return verses(1, 12)
