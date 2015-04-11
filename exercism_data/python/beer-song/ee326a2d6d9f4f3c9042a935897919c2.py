from sys import exit

class Beer(object):
	def __init__(self):
		self.beer = self

	def bottles(self, beers):	
		count = str(beers) if beers > 0 else "no more"	
		num_bottles = " bottle" if beers == 1 else " bottles"
		return count + num_bottles
	
	def action(self, beers):
		if beers < 0:
			exit("Enter a positive number please")
		elif beers == 0:
			return "Go to the store and buy some more, "
		else:
			return "Take %s down and pass it around, "%("one" if beers > 1 else "it")

	def remainder(self, beers):
		if beers == 1:
			return "no more"
		return str(beers-1) if beers > 1 else "99"

	def end_line(self, beers):	
		return "%s of beer on the wall.\n" %("bottle" if beers == 2 else "bottles")

	def verse(self, beers):
		line1 = self.bottles(beers) + " of beer on the wall, "
		line2 = self.bottles(beers) + " of beer.\n"
		line3 = self.action(beers)
		line4 = self.remainder(beers) + " " + self.end_line(beers)
		return line1.capitalize() + line2 + line3 + line4
	
	def sing(self, start, end=0):	
		song = []
		beers = start
		while beers >= end:
			verse = self.verse(beers)
			song.extend([verse, '\n'])
			beers -= 1
		return "".join(song)
