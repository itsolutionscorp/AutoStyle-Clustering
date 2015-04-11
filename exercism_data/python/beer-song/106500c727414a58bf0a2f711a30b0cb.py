def song(start, stop=None):
    stop = stop - 1 if stop is not None else -1
    verses = [verse(i) for i in range(start, stop, -1)]
    return '\n'.join(verses) + '\n'


def verse(num):
    what = "bottle{} of beer"
    where = "on the wall"
    action = "Take {} down and pass it around"
    actions = {
        1: action.format('it'),
        0: "Go to the store and buy some more"
    }
    how_many = {
        0: 'no more',
        -1: 99
    }
    whats = {
        1: what.format('')
    }
    how_many_1 = how_many.get(num, str(num))
    return '{0} {1} {2}, {3} {1}.\n{4}, {5} {6} {2}.\n'.format(
        how_many_1.capitalize(),
        whats.get(num, what.format('s')),
        where,
        how_many_1,
        actions.get(num, action.format('one')),
        how_many.get(num - 1, num - 1),
        whats.get(num - 1, what.format('s')),
        where
    )
