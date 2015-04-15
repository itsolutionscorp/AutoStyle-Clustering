# vim:fileencoding=utf-8


VERSE_THINGS = [
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
INDEX_TO_ENG = [
    'NOPE',
    'first',
    'second',
    'third',
    'fourth',
    'fifth',
    'sixth',
    'seventh',
    'eighth',
    'ninth',
    'tenth',
    'eleventh',
    'twelfth',
]


def sing():
    return verses(1, 12)


def verse(verse_number):
    output_verse_things = VERSE_THINGS[:verse_number]
    return '{}, {}.\n'.format(
        _leader(verse_number),
        ', '.join([
            _maybe_anded(verse_number, len(output_verse_things) - i, thing)
            for i, thing in enumerate(reversed(output_verse_things))
            if i <= verse_number
        ])
    )


def verses(start, finish):
    return ''.join([
        verse(n) + '\n' for n in reversed(
            list(reversed(range(start, finish + 1)))
        )
    ])


def _leader(verse_number):
    return ('On the {} day of Christmas my true love '
            'gave to me').format(INDEX_TO_ENG[verse_number])


def _maybe_anded(verse_number, thing_number, thing):
    if verse_number > 1 and thing_number == 1:
        return 'and ' + thing
    return thing
