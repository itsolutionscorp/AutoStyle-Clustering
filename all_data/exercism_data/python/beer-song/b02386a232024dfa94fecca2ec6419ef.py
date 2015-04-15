class Beer:
	def __init__(self):
		self.X_BOTTLES = "x bottles of beer on the wall, x bottles of beer.\n"
		self.Y_BOTTLES = "Take one down and pass it around, y bottles of beer on the wall.\n"
		self.NO_MORE_BOTTLES = "No more bottles of beer on the wall, no more bottles of beer.\n" + "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

	def verse(self, verse_no):
		
		if verse_no == 1:
			verse_str = self.X_BOTTLES.replace("bottles", "bottle").replace("x", str(verse_no))		
			verse_str += self.Y_BOTTLES.replace("y", "no more").replace("one", "it")
		elif verse_no == 0:
			verse_str = self.NO_MORE_BOTTLES
		else:
			verse_str = self.X_BOTTLES.replace("x", str(verse_no))			
			if (verse_no - 1) == 1:
				verse_str += self.Y_BOTTLES.replace("y", str(verse_no - 1)).replace("bottles", "bottle")
			else:
				verse_str += self.Y_BOTTLES.replace("y", str(verse_no - 1))

		return verse_str	

	def sing(self, verse_no_from, verse_no_to = 0):
		verse_str = ""
		for i in reversed(range(verse_no_to, verse_no_from + 1)):
			verse_str += self.verse(i)
			verse_str += "\n"

		return  verse_str


	
