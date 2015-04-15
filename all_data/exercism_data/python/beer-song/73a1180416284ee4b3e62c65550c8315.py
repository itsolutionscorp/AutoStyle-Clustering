from collections import defaultdict

VERSE = """{0} bottle{before_plural} of beer on the wall, {1} bottle{before_plural} of beer.
{action}, {after} bottle{after_plural} of beer on the wall.
"""

DEFAULT_ACTION = 'Take {} down and pass it around'
ACTIONS = defaultdict(lambda: DEFAULT_ACTION.format('one'))
ACTIONS[1] = DEFAULT_ACTION.format('it')
ACTIONS[0] = 'Go to the store and buy some more'


def get_number(n):
    if n:
        return n, n

    return 'No more', 'no more'


def get_plural(n):
    return '' if n == 1 else 's'


def verse(n):
    n_after = n - 1 if n else 99
    return VERSE.format(*get_number(n), action=ACTIONS[n], after=get_number(n_after)[1],
                        before_plural=get_plural(n), after_plural=get_plural(n_after))


def song(verse_from, verse_to=0):
    return '\n'.join(verse(n) for n in range(verse_from, verse_to - 1, -1)) + '\n'
