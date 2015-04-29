from collections import OrderedDict
	
class Allergies(object):
	allergies = OrderedDict([("eggs", 1),
                          ("peanuts", 2),
                          ("shellfish", 4),
                          ("strawberries", 8),
                          ("tomatoes", 16),
                          ("chocolate", 32),
                          ("pollen", 64),
                          ("cats", 128)])

	def __init__(self, n):
		self.numberofallergies = n

	def is_allergic_to(self, allergie):
		return bool(self.numberofallergies & self.allergies[allergie])

	@property
	def list(self):
		return [allergie for allergie in self.allergies if self.is_allergic_to(allergie)]
