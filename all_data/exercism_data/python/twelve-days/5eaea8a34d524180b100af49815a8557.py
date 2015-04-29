preamble = 'On the {} day of Christmas my true love gave to me, '
cardinals = 'first second third fourth fifth sixth seventh eighth ninth ' \
        'tenth eleventh twelfth'.split()
gifts = [
    'twelve Drummers Drumming',
    'eleven Pipers Piping',
    'ten Lords-a-Leaping',
    'nine Ladies Dancing',
    'eight Maids-a-Milking',
    'seven Swans-a-Swimming',
    'six Geese-a-Laying',
    'five Gold Rings',
    'four Calling Birds',
    'three French Hens',
    'two Turtle Doves'
    ]
sep = ', '
finalsep = sep + 'and '
end = 'a Partridge in a Pear Tree.\n'

def verse(n):
    v = preamble.format(cardinals[n-1])
    if n > 1:
        v += sep.join(gifts[12 - n:]) + finalsep
    return v + end

def verses(m, n):
    s = ''
    for i in range(m, n+1):
        s += verse(i) + '\n'
    return s

def sing():
    return verses(1, 12)
