class Beer:
	def verse(self, count):
	
		return (self.wall(count) + self.pass_beer(count))
				
	def sing(self, *args):
		start = args[0]
		if (len(args) == 1):
			end = 0
		else:
			end = args[1]
		song = ''
		for line in range(start - end + 1):
			song += self.verse(start - line)
			song += '\n'
		return song
		
	def wall(self, count):
		if count == 0:
			return "No more bottles of beer on the wall, no more bottles of beer.\n"
		elif count == 1:
			return "1 bottle of beer on the wall, 1 bottle of beer.\n"
		else:
			return "{0} bottles of beer on the wall, {0} bottles of beer.\n".format(str(count))
		
	def pass_beer(self, count):
		count -= 1
		if count == 0:
			return "Take it down and pass it around, no more bottles of beer on the wall.\n"
		elif count < 0:
			return "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
		elif count == 1:
			return "Take one down and pass it around, 1 bottle of beer on the wall.\n"
		else:
			return "Take one down and pass it around, {0} bottles of beer on the wall.\n".format(str(count))
