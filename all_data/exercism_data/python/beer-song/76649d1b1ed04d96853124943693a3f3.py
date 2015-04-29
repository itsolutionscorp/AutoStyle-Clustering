# vim:fileencoding=utf-8


VERSE_TMPL = \
    '{cur} {cur_bottles} of beer on the wall, ' \
    '{cur} {cur_bottles} of beer.\n' \
    'Take one down and pass it around, ' \
    '{next} {next_bottles} of beer on the wall.\n'
SPECIAL_VERSES = {
    1: '1 bottle of beer on the wall, 1 bottle of beer.\n'
       'Take it down and pass it around, no more bottles of '
       'beer on the wall.\n',
    0: 'No more bottles of beer on the wall, no more bottles of beer.\n'
       'Go to the store and buy some more, 99 bottles of beer on the wall.\n'
}


def song(start=99, finish=0):
    return '\n'.join([
        verse(n) for n in reversed(range(finish, start + 1))
    ]) + '\n'


def verse(verse_number):
    special_verse = SPECIAL_VERSES.get(verse_number)
    if special_verse is not None:
        return special_verse

    tmpl_vars = {
        'cur': verse_number,
        'cur_bottles': _bottles(verse_number),
        'next': verse_number - 1,
        'next_bottles': _bottles(verse_number - 1),
    }
    return VERSE_TMPL.format(**tmpl_vars)


def _bottles(n):
    if n == 1:
        return 'bottle'
    return 'bottles'
