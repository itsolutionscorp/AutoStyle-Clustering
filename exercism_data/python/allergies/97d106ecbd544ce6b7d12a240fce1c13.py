"""
* eggs (1)
* peanuts (2)
* shellfish (4)
* strawberries (8)
* tomatoes (16)
* chocolate (32)
* pollen (64)
* cats (128)
"""

allergens = {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries',
					16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'}



def allergenerator(num):
		allergy_list = []
		if num > 255: num -= 256

		while num > 0:
			if num == 255:
				allergy_list =('eggs peanuts shellfish strawberries tomatoes '
             'chocolate pollen cats').split()
				num -= 255
				continue
			if num in [key for key in allergens]:
				allergy_list.append(allergens[num])
				num = 0
			else:
				diff = max([x for x in allergens if x < num])
				if allergens[diff] not in allergy_list:
					allergy_list.append(allergens[diff])
				num -= diff
		return allergy_list

class Allergies:
	
				

	def __init__(self, num):
		self.allergy_num = num
		self.list = allergenerator(num)

	def is_allergic_to(self, food):
		
		return True if food in self.list else False


	
