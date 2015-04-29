class Beer(object):
	def sing(self, beers=99, min_beers=0):
		beer_song = []
		while beers >= min_beers:
			beer_song.extend([self.verse(beers), "\n"])
			beers -= 1
		return ''.join(beer_song)

	def verse(self, beers):
		song = ["%s of beer on the wall, " % self.bottles(beers).capitalize(),
		"%s of beer.\n" % self.bottles(beers),
		self.action(beers),
		self.rephrase(beers)]
		return ''.join(song)

	def action(self, beers):
		if 0 == beers:
			return "Go to the store and buy some more, " 
		return "Take %s down and pass it around, " % ("one" if beers > 1 else "it")

	def rephrase(self, beers):
		subtract_a_beer = beers-1 if beers > 0 else 99
		return "%s of beer on the wall.\n" % self.bottles(subtract_a_beer)

	def bottles(self, beers):
		num = str(beers) if beers > 0 else "no more"
		glass = " bottle" if 1 == beers else " bottles"
		return num + glass
