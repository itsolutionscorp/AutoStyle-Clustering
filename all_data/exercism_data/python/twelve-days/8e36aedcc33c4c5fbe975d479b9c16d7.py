from string import Template


DAYS = {1: 'first',
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
        12: 'twelfth'}

GIFTS = {1: 'a Partridge in a Pear Tree.',
         2: 'two Turtle Doves, and ',
         3: 'three French Hens, ',
         4: 'four Calling Birds, ',
         5: 'five Gold Rings, ',
         6: 'six Geese-a-Laying, ',
         7: 'seven Swans-a-Swimming, ',
         8: 'eight Maids-a-Milking, ',
         9: 'nine Ladies Dancing, ',
         10: 'ten Lords-a-Leaping, ',
         11: 'eleven Pipers Piping, ',
         12: 'twelve Drummers Drumming, '}


def sing():
    return verses(12, 1)


def verse(verse_to_return):
    return format_one_verse(verse_to_return)


def verses(gift_to_end_with, gift_to_start_with):
    song = ''
    for current_verse in range(gift_to_start_with, gift_to_end_with+1):
        song += format_one_verse(current_verse) + '\n'
    return song


def format_one_verse(verse_to_format):
    this_verse = day_number_string_interpolation(verse_to_format)
    for gift in range(verse_to_format, 0, -1):
        this_verse += GIFTS[gift]
    this_verse += '\n'
    return this_verse



def day_number_string_interpolation(day):
    tmpl = Template("On the $num_in_words day of Christmas my true love gave to me, ")
    return tmpl.substitute(num_in_words=DAYS[day])
