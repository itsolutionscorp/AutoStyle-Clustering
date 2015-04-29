def verse(i):
    out = '%s bottles of beer on the wall, %s bottles of beer.\nTake one down and pass it around, %s bottles of beer on the wall.\n' % (i, i, i-1)
    if i == 2:
        out = '2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n'
    if i == 1:
        out = '1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n'
    if i == 0:
        out = 'No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n'
    return out

def song(start, end=0):
    verses = range(start, end-1, -1)
    verses = [verse(i) for i in verses]
    return '%s\n' % '\n'.join(verses)
