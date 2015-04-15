class Beer:
	def verse(self, n):
		vs = ["No more bottles of beer on the wall, no more bottles of beer.\n"
			       "Go to the store and buy some more, 99 bottles of beer on the wall.\n",
			"1 bottle of beer on the wall, 1 bottle of beer.\n"
	                  "Take it down and pass it around, no more bottles of beer on the wall.\n",
			"2 bottles of beer on the wall, 2 bottles of beer.\n"
		            "Take one down and pass it around, 1 bottle of beer on the wall.\n",
		]
		if n < len(vs):
			return vs[n]
		return "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d bottles of beer on the wall.\n" % (n, n, n-1)

	def sing(self, start, end=0):
		if start < end:
			tmp = start
			start = end
			end = tmp
		vs = [self.verse(n) for n in range(start, end-1, -1)]
		return "\n".join(vs)+"\n"
