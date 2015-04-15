gifts = [(),
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
         ('twelfth', 'twelve Drummers Drumming')]


def verse(v):
    nth = gifts[v][0]
    if v == 1:
        gift_list = [gifts[1][1]]
    else:
        gift_list = [a[1] for a in gifts[2:v+1]]
        gift_list.reverse()
        gift_list.append('and %s' % gifts[1][1])
    return 'On the %s day of Christmas my true love gave to me, %s.\n' % (nth, ', '.join(gift_list))


def verses(a, b):
    return '%s\n' % '\n'.join([verse(i) for i in range(a, b+1)])


def sing():
    return verses(1, 12)
