allergies = [
	"eggs",
	"peanuts",
	"shellfish",
	"strawberries",
	"tomatoes",
	"chocolate",
	"pollen",
	"cats"
]

class Allergies(object):

	def __init__(self, allergy_mask):
		self.list = [
			allergy
			for index, allergy in enumerate(allergies)
			if allergy_mask & 2**index
		]

	def is_allergic_to(self, allergy):
		return allergy in self.list
