def sing():
    return verses(1, 12)


def verse(num):
    days = (
        (None, None),
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
    )
    song = ['On the {0} day of Christmas my true love gave to me, {1}'.format(
        *days[num]
    )]
    for i in range(num - 1, 0, -1):
        song.append('{0} {1}'.format(' and' if i == 1 else '', days[i][1]))
    return ','.join(song) + '.\n'


def verses(start, stop):
    return '\n'.join(verse(i) for i in range(start, stop + 1)) + '\n'
