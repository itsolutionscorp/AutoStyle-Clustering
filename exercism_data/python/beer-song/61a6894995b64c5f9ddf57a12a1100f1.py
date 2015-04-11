def onthewall(index):
	if(index>1):
		return "%s bottles of beer on the wall" % (index)
	if(index==1):
		return "%s bottle of beer on the wall" % (index)
	if(index==0):
		return "No more bottles of beer on the wall"


def leftonthewall(index):
	index = 99 if index==-1 else index
	if(index>1):
		return "%s bottles of beer on the wall" % (index)
	if(index==1):
		return "%s bottle of beer on the wall" % (index)
	if(index==0):
		return "no more bottles of beer on the wall"


def bottlesofbeer(index):
	if(index>1):
		return "%s bottles of beer" % (index)
	if(index==1):
		return "%s bottle of beer" % (index)
	if(index==0):
		return "no more bottles of beer"


def action(index):
	if(index>1):
		return "Take one down and pass it around"
	if(index==1):
		return "Take it down and pass it around"
	if(index==0):
		return "Go to the store and buy some more"


def verse(index):
	return "%s, %s.\n%s, %s.\n" % (onthewall(index), bottlesofbeer(index), action(index), leftonthewall(index-1))


def song(fromindex, toindex=0):
	countdown = range(toindex, fromindex+1)
	countdown.reverse()
	verses = []
	for i in countdown:
		verses.append(verse(i))

	verses.append("")  # appending empty item to list for the last \n.
	return "\n".join(verses)
