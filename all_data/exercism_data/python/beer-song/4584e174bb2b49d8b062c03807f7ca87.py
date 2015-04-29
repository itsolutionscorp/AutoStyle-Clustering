class Beer(object):
	global template
	template = "%s bottles of beer on the wall, %s bottles of beer.\nTake one down and pass it around, %s bottles of beer on the wall.\n"

	def verse(self, n):
		if n > 2:
			return template %(n, n, (n-1))

		if n == 2:
			return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"

		if n == 1:
			return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		if n == 0:
			return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

	def sing(self, start, end=0):
		verses = ""
		for n in reversed(xrange(end, start+1)):
			verses += (self.verse(n) + '\n')
		return verses
