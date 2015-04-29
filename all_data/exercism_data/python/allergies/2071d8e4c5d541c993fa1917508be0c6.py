class Allergies(object):
	"""
	Is an object with the list of substances that a patient is allergic
	Possible substances to be allergic:
		* eggs (1)
		* peanuts (2)
		* shellfish (4)
		* strawberries (8)
		* tomatoes (16)
		* chocolate (32)
		* pollen (64)
		* cats (128)
	"""
	substances = [("eggs", 1), ("peanuts", 2),("shellfish", 4), ("strawberries",8), ("tomatoes", 16),
				  ("chocolate", 32), ("pollen", 64), ("cats", 128)]

	def __init__(self, num):
		"""
		::num:: is an integer
		::list:: create a list with the substances the patient is allergic 
		"""
		self.list = []
		self.bin_num = list(bin(num)[2:])
		self.bin_num.reverse()
		for n in range(len(self.bin_num)):
			if self.bin_num[n] == '1' and n<= len(self.substances) -1:
				self.list.append(self.substances[n][0])

	def is_allergic_to(self, substance):
		"""
		Return:: True is the patients is allergic to the substance
		"""
		if substance in self.list:
			return True
		return False

	def __str__(self):
		return "The patient is allergic to: %s" % self.list
