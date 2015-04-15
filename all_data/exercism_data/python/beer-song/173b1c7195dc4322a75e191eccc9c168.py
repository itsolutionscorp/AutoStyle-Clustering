def song(start,end=0):
	return '\n'.join([verse(n) for n in range(start, end-1, -1)]) + "\n"


VERSES = {
0: 'No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n',
1: '1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n',
2: '2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n'
}

DEFAULT_VERSE = '{n} bottles of beer on the wall, {n} bottles of beer.\nTake one down and pass it around, {nminus} bottles of beer on the wall.\n'

def verse(n):
	return VERSES.get(n, DEFAULT_VERSE.format(n=n, nminus=n-1))
