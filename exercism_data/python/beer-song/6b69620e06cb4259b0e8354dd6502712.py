class Beer:
	
	def verse(self, number):
		current = "{:d} bottles of beer".format(number)
		description = "one";
		
		if number == 0:
			return ("No more bottles of beer on the wall, no more bottles of beer.\n"
			+"Go to the store and buy some more, 99 bottles of beer on the wall.\n")
		elif number == 2:
			less_one = "1 bottle of beer"
		elif number == 1:
			current = "1 bottle of beer"
			less_one = "no more bottles of beer"
			description = "it";
		else:
			less_one = "{:d} bottles of beer".format(number-1)
		
		words = ("{:s} on the wall, {:s}.\n"
		+"Take {:s} down and pass it around, {:s} on the wall.\n").format(current, current, description, less_one)
		return words
		
	def sing(self, number, stop_at = 0):
		song = []
		for n in range(number, stop_at-1, -1):
			song.append(self.verse(n))
		return "\n".join(song) + "\n"
