def verse(n):
    if 0 == n:
        return 'No more bottles of beer on the wall, no more bottles of beer.\n' + \
               'Go to the store and buy some more, 99 bottles of beer on the wall.\n'

    line = '{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.\n' + \
           'Take {2} down and pass it around, {3} bottle{4} of beer on the wall.\n'
    return line.format(n, ['', 's'][n > 1], ['it', 'one'][n > 1],
                       ['no more', n - 1][n > 1], ['', 's'][n != 2])


def song(n, m=0):
    return '\n'.join(verse(i) for i in range(n, m - 1, -1)) + '\n'
