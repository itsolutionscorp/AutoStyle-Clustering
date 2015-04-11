import string

class Word(object):

	points_ref = dict()

	def __init__(self, word):
		self.word = word.rstrip().lower()
		self.build_points()

	def score(self):
		return sum([self.points_ref[letter] for letter in self.word])

	def is_vowel(self, letter):
		return (letter == 'a' or letter == 'e' or letter == 'i' or letter == 'o' or letter == 'u')

	def is_one_pointer(self, letter):
		return (letter == 'l' or letter == 'n' or letter == 'r' or letter == 's' or letter == 't' or self.is_vowel(letter))

	def is_two_pointer(self, letter):
		return (letter == 'd' or letter == 'g')

	def is_three_pointer(self, letter):
		return (letter == 'b' or letter == 'c' or letter == 'm' or letter == 'p')
	
	def is_four_pointer(self, letter):
		return (letter == 'f' or letter == 'h' or letter == 'v' or letter == 'w' or letter =='y')
	
	def is_five_pointer(self, letter):
		return (letter == 'k')

	def is_eight_pointer(self, letter):
		return (letter == 'j' or letter == 'x')

	def is_ten_pointer(self, letter):
		return (letter == 'q' or letter == 'z')


	def build_points(self):

		for letter in string.ascii_lowercase:
			if self.is_one_pointer(letter):
				self.points_ref[letter] = 1
			elif self.is_two_pointer(letter):
				self.points_ref[letter] = 2
			elif self.is_three_pointer(letter):
				self.points_ref[letter] = 3
			elif self.is_four_pointer(letter):
				self.points_ref[letter] = 4
			elif self.is_five_pointer(letter):
				self.points_ref[letter] = 5
			elif self.is_eight_pointer(letter):
				self.points_ref[letter] = 8
			else:
				self.points_ref[letter] = 10
