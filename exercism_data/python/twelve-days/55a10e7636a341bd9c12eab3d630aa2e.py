def verse(n):
    THINGS = {
            12: 'twelve Drummers Drumming',
            11: 'eleven Pipers Piping',
            10: 'ten Lords-a-Leaping',
            9: 'nine Ladies Dancing',
            8: 'eight Maids-a-Milking',
            7: 'seven Swans-a-Swimming',
            6: 'six Geese-a-Laying',
            5: 'five Gold Rings',
            4: 'four Calling Birds',
            3: 'three French Hens',
            2: 'two Turtle Doves',
            1: 'a Partridge in a Pear Tree'
            }
    NUMBER = {
            1: 'first',
            2: 'second',
            3: 'third',
            4: 'fourth',
            5: 'fifth',
            6: 'sixth',
            7: 'seventh',
            8: 'eighth',
            9: 'ninth',
            10: 'tenth',
            11: 'eleventh',
            12: 'twelfth'
            }
    if n == 1:
        stuffs = THINGS[1]
    else:
        stuffs = ', '.join([THINGS[i] for i in range(n, 1, -1)]) + ', and ' + THINGS[1]
    return "On the {:s} day of Christmas my true love gave to me, {:s}.\n".format(NUMBER[n], stuffs)

def sing():
    return verses(1, 12)

def verses(begin, end):
    return "\n".join([verse(i) for i in range(begin, end + 1)]) + "\n"
