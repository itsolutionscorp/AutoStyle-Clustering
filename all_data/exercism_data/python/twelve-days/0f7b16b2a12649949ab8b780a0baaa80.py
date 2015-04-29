day = {key: dict(zip(('ordinal', 'gift'), val)) for key, val in
       {1: ('first', 'a Partridge in a Pear Tree'),
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
        }.iteritems()}

def verse(n):
    message = 'On the {} day of Christmas my true love gave to me, {}.\n'
    if n == 1:
        return message.format(day[1]['ordinal'], day[1]['gift'])
    else:
        return message.format(
            day[n]['ordinal'],
            (', '.join(day[n]['gift'] for n in range(n, 1, -1)) +
             ', and ' + day[1]['gift']))

def verses(n, m):
    return '\n'.join(verse(x) for x in range(n, m + 1)) + '\n'

def sing():
    return verses(1, 12)
