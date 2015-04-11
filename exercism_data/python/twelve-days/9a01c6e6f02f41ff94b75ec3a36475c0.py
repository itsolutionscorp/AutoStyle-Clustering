def verse(n):
    gift_list = gifts(n)
    if len(gift_list) > 1:
        gift_list[-1] = 'and ' + gift_list[-1]
    gift_string = ', '.join(gift_list)
    template = "On the {0} day of Christmas my true love gave to me, {1}.\n"
    return template.format(day(n), gift_string)

def verses(start, end):
    song = [verse(i) for i in range(start, end+1)]
    return "\n".join(song) + "\n"

def sing():
    return verses(1, 12)

### private ###

DAYS = [ 'first', 'second', 'third', 'fourth',
        'fifth', 'sixth', 'seventh', 'eighth',
        'ninth', 'tenth', 'eleventh', 'twelfth' ]

GIFTS = [
        'a Partridge in a Pear Tree',
        'two Turtle Doves',
        'three French Hens',
        'four Calling Birds',
        'five Gold Rings',
        'six Geese-a-Laying',
        'seven Swans-a-Swimming',
        'eight Maids-a-Milking',
        'nine Ladies Dancing',
        'ten Lords-a-Leaping',
        'eleven Pipers Piping',
        'twelve Drummers Drumming',
        ]

def day(n):
    return DAYS[n-1]

def gifts(n):
    return list(reversed(GIFTS[0:n]))
