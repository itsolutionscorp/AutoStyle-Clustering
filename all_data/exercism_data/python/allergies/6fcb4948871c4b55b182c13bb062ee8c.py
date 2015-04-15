class Allergies:
	

	def list_allergens(self):
		current_allergen_number = self.allergen_number
		allergen_coefficient = {v: k for k, v in self.possible_allergens.iteritems()}
		allergen_count = [int(k) for k in allergen_coefficient]
		allergen_count.remove(1)
		allergen_count = sorted(allergen_count, key=int, reverse=True)
		list_of_allergens = []

		#Fast way to check allergen to simplify the latter process
		if current_allergen_number % 2 != 0:
			list_of_allergens.append(allergen_coefficient[1])
			current_allergen_number=current_allergen_number-1

		#Finds if input is out of range
		if current_allergen_number < 1 or current_allergen_number > 255:
			return list_of_allergens

		#Brute Forcing all Possible Answers
		for i in allergen_count:
			if current_allergen_number == i:
				list_of_allergens.append(allergen_coefficient[i])
				return list_of_allergens
			for b in allergen_count:
				if current_allergen_number == i+b:
					list_of_allergens.append(allergen_coefficient[b])
					list_of_allergens.append(allergen_coefficient[i])
					return list_of_allergens
				for c in allergen_count:
					if current_allergen_number == i+b+c:
						list_of_allergens.append(allergen_coefficient[c])
						list_of_allergens.append(allergen_coefficient[b])
						list_of_allergens.append(allergen_coefficient[i])
						return list_of_allergens
					for d in allergen_count:
						if current_allergen_number == i+b+c+d:
							list_of_allergens.append(allergen_coefficient[d])
							list_of_allergens.append(allergen_coefficient[c])
							list_of_allergens.append(allergen_coefficient[b])
							list_of_allergens.append(allergen_coefficient[i])
							return list_of_allergens
						for e in allergen_count:
							if current_allergen_number == i+b+c+d+e:
								list_of_allergens.append(allergen_coefficient[e])
								list_of_allergens.append(allergen_coefficient[d])
								list_of_allergens.append(allergen_coefficient[c])
								list_of_allergens.append(allergen_coefficient[b])
								list_of_allergens.append(allergen_coefficient[i])
								return list_of_allergens
							for f in allergen_count:
								if current_allergen_number == i+b+c+d+e+f:
									list_of_allergens.append(allergen_coefficient[f])
									list_of_allergens.append(allergen_coefficient[e])
									list_of_allergens.append(allergen_coefficient[d])
									list_of_allergens.append(allergen_coefficient[c])
									list_of_allergens.append(allergen_coefficient[b])
									list_of_allergens.append(allergen_coefficient[i])
									return list_of_allergens
								for g in allergen_count:
									if current_allergen_number == i+b+c+d+e+f+g:
										list_of_allergens.append(allergen_coefficient[g])
										list_of_allergens.append(allergen_coefficient[f])
										list_of_allergens.append(allergen_coefficient[e])
										list_of_allergens.append(allergen_coefficient[d])
										list_of_allergens.append(allergen_coefficient[c])
										list_of_allergens.append(allergen_coefficient[b])
										list_of_allergens.append(allergen_coefficient[i])
										return list_of_allergens

		return list_of_allergens

	def __init__(self, number):
		self.allergen_number = number
		self.possible_allergens={'eggs':1,'peanuts':2,'shellfish':4,'strawberries':8,'tomatoes':16,'chocolate':32,'pollen':64,'cats':128}
		self.list = self.list_allergens()

	def is_allergic_to(self, allergen):
		current_allergen_number = self.allergen_number
		if current_allergen_number < 1:
			return False
		if current_allergen_number % 2 != 0:
			if self.possible_allergens[allergen] == 1:
				return True
			else:
				current_allergen_number = current_allergen_number-1
		while current_allergen_number != 1:
			if current_allergen_number == self.possible_allergens[allergen]:
				return True
			current_allergen_number=current_allergen_number/2
	
	
