class Beer:
	VERSE = "{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.\n"\
			"Take {2} down and pass it around, {3} bottle{4} of beer on the wall.\n"
	VERSE0 = "No more bottles of beer on the wall, no more bottles of beer.\n"\
			"Go to the store and buy some more, 99 bottles of beer on the wall.\n"

	def verse(self, v):
		if v >= 1:
			return self.VERSE.format(v, 
									"s"[v==1:], 
									"it" if v==1 else "one", 
									"no more" if v==1 else v-1,
									 "s"[v==2:])
		else:
			return self.VERSE0

	def sing(self, start, end = 0):
		output = ""
		while(start >= end):
			output += self.verse(start) + "\n"
			start -= 1
		return output
