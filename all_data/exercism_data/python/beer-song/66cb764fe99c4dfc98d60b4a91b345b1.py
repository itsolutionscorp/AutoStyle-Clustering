def verse(n):
    line = '{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.\n' + \
           'Take {2} down and pass it around, {3} bottle{4} of beer on the wall.\n'
    return line.format(n, ['', 's'][n > 1], ['it', 'one'][n > 1],
                       ['no more', n - 1][n > 1], ['', 's'][n != 2])

def song():
    return 0


if __name__=='__main__':
    print(verse(1))
