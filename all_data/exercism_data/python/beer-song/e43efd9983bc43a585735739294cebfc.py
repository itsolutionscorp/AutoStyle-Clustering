class Beer:
	def __init__(self, number, next_beer):
		self.next_beer = next_beer
		self.number = str(number)

	def how_many(self):
		return "{0} {1} of beer".format(self.number, self.bottle_noun())

	def bottle_noun(self):
		return "bottles"

	def pronoun(self):
		return "one"

	def do_action(self):
		return self.next_beer

	def next_action(self):
		phrase = "Take {0} down and pass it around"
		return phrase.format(self.pronoun())
		
class NoBeer(Beer):
	def __init__(self):
		self.number = "no more"

	def set_next_action(self, next_beer):
		self.next_beer = next_beer

	def next_action(self):
		return "Go to the store and buy some more"
		

class OneBeer(Beer):
	def __init__(self, next_beer):
		Beer.__init__(self, 1, next_beer)

	def pronoun(self):
		return "it"

	def bottle_noun(self):
		return "bottle"

class Wall:
	@staticmethod
	def install_beers():
		no_beer = NoBeer()
		Wall.BEERS = [no_beer, OneBeer(no_beer)]
		for num in range(2, 100):
			Wall.BEERS.append(
				Beer(num, Wall.BEERS[-1]))
		no_beer.set_next_action(Wall.BEERS[-1])

	def __init__(self):
		self.verse = ""

	def sing(self, words):
		self.verse += words
		return self
	
	def next_clause(self):
		return self.sing(", ")
		
	def on_the_wall(self):
		return self.sing(" on the wall")

	def end_phrase(self):
		return self.sing(".\n")

	def sing_verse(self, number):
		bottles = Wall.BEERS[number]
		sing = self.sing
		sing(bottles.how_many().capitalize()
			).on_the_wall().next_clause()
		sing(bottles.how_many()).end_phrase()
		sing(bottles.next_action()).next_clause()
		bottles = bottles.do_action()
		sing(bottles.how_many()).on_the_wall().end_phrase()
		return self.full_verse()
	
	def full_verse(self):
		verse = self.verse
		self.verse = ''
		return verse
	
	def sing_song(self, first, last):
		song = ""
		for i in range(first, last-1, -1):
			song += self.sing_verse(i) + "\n"
		return song
	
Wall.install_beers()
WALL = Wall()

def song(first,last=0):
	return WALL.sing_song(first, last)

def verse(n):
	return WALL.sing_verse(n)
