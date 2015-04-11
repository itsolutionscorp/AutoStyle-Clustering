_ORDINALS = {1: 'first', 2: 'second', 3: 'third', 5: 'fifth',
             8: 'eighth', 9: 'ninth', 12: 'twelfth'}
_NUMERALS = ('zero', 'one',   'two',  'three', 'four',   'five',   'six', 'seven',
             'eight', 'nine', 'ten',   'eleven', 'twelve')
_INTRO = "On the %s day of Christmas my true love gave to me"
_END = ".\n"
_CHRISTMAS_GIFTS = {1: 'Partridge in a Pear Tree',
                    2: 'Turtle Doves',
                    3: 'French Hens',
                    4: 'Calling Birds',
                    5: 'Gold Rings',
                    6: 'Geese-a-Laying',
                    7: 'Swans-a-Swimming',
                    8: 'Maids-a-Milking',
                    9: 'Ladies Dancing',
                   10: 'Lords-a-Leaping',
                   11: 'Pipers Piping',
                   12: 'Drummers Drumming'
}


def _ordinal_from_number(num):
    ordinal = _ORDINALS.get(num, None)
    if ordinal is None:
        suffix = 'th'
        numeral = _NUMERALS[num]
        ordinal = "%s%s" % (numeral, suffix)
    return ordinal

def _format_gifts(day):
    first_gift = _CHRISTMAS_GIFTS[1]
    gifts = tuple(_CHRISTMAS_GIFTS[d] for d in range(day, 1, -1))
    numerals = tuple(_NUMERALS[d] for d in range(day, 1, -1))
    gift_list = []
    for gift_pair in zip(numerals, gifts):
        gift_list.append("%s %s" % gift_pair)
    if gifts:
        gift_list.append("and a %s" % first_gift)
    else:
        gift_list.append("a %s" % first_gift)

    return ", ".join(gift_list)
    

def verse(day):
    if not 0 < day < 13:
        raise ValueError('Day should be greater than 0 and less than 13, got %d.' % day)
    ordinal = _ordinal_from_number(day)
    gifts = _format_gifts(day)
    return ", ".join((_INTRO % ordinal, gifts)) + _END

def verses(start_day, end_day):
    return "\n".join(verse(day) for day in range(start_day, end_day + 1)) + '\n'

def sing():
    return verses(1, 12)
