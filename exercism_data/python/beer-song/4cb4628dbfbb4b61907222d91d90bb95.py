def verse(n):
    line = '{0} bottles of beer on the wall, {0} bottles of beer.\n' + \
           'Take one down and pass it around, {1} bottles of beer on the wall.\n'
    return line.format(n, n - 1)

def song():
    return 0


if __name__=='__main__':
    print(verse(8))
