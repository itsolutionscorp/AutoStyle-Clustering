def verse(versenum):
	'''
	return a single verse of the 99 bottles of beer song
	'''
	if versenum==0:
		return( "No more bottles of beer on the wall, no more bottles of beer.\n" \
			"Go to the store and buy some more, 99 bottles of beer on the wall.\n")

	elif versenum==1:
		return( "1 bottle of beer on the wall, 1 bottle of beer.\n" \
			"Take it down and pass it around, no more bottles of beer on the wall.\n")

	elif versenum==2:
		return( "2 bottles of beer on the wall, 2 bottles of beer.\n" \
			"Take one down and pass it around, 1 bottle of beer on the wall.\n")

	else:
		return( "{} bottles of beer on the wall, {} bottles of beer.\n" \
			"Take one down and pass it around, {} bottles of beer on the wall.\n" \
			.format(versenum,versenum,versenum-1))

def song(firstverse,lastverse=0):
	'''
	return a range of verses of the 99 bottles of beer song
	'''
	return '\n'.join(verse(versenum) for versenum in range(firstverse,lastverse-1,-1))+'\n'



if __name__=='__main__':
	print(song(3))
