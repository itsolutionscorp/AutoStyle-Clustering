class Allergies:

	def __init__(self, n):
		self.n_list_big = [1,2,4,8,16,32,64,128]
		self.a_dict = dict(zip([1,2,4,8,16,32,64,128], ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']))
		if n > 255:
			n = n % 255 - 1
		self.n = n
		self.lst = []
		self.n_list = [x for x in self.n_list_big if x <= n]
		self.a_list = self.find_allergies(self.lst, self.n_list, self.n)
		self.list = []
		for i in self.a_list:
			self.list = self.list + [self.a_dict[i]]
		self.list.reverse()
		
	def is_allergic_to(self, allergen):
		return allergen in self.list
		
	def find_allergies(self, lst, n_list, n):
		if sum(lst) == n:
			return lst
		elif not n_list or sum(lst) > n:
			return []
		elif n_list[-1] == n:
			return [n_list[-1]]
		else:
			return self.find_allergies(lst + [n_list[-1]], n_list[0:len(n_list)-1], n) + self.find_allergies(lst, n_list[0:len(n_list)-1], n)
