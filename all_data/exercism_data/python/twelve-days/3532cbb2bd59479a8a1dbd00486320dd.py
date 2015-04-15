phrases = [
        "On the {0} day of Christmas my true love gave to me,",
        ('first', ' a Partridge in a Pear Tree.'),
        ('second', ' two Turtle Doves,'),
        ('third', ' three French Hens,'),
        ('fourth', ' four Calling Birds,'),
        ('fifth', ' five Gold Rings,'),
        ('sixth', ' six Geese-a-Laying,'),
        ('seventh', ' seven Swans-a-Swimming,'),
        ('eighth', ' eight Maids-a-Milking,'),
        ('ninth', ' nine Ladies Dancing,'),
        ('tenth', ' ten Lords-a-Leaping,'),
        ('eleventh', ' eleven Pipers Piping,'),
        ('twelfth', ' twelve Drummers Drumming,'),
        ]

def sing():
    return verses(1, 12)

def verse(num):
    parts = [phrases[0].format(phrases[num][0])]
    i = num
    while i >= 1:
        parts.append(phrases[i][1])
        i -= 1
    if num > 1:
        parts.insert(-1, ' and')
    parts.append('\n')
    return ''.join(parts)

def verses(start, end):
    return '\n'.join([verse(i) for i in xrange(start, end + 1)] + [''])
