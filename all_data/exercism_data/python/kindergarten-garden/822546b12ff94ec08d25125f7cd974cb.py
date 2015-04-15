class Garden:
	children = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
	plant_dict = {"G": "Grass", "R": "Radishes", "C": "Clover", "V": "Violets"}
	childrens_plants = {}
	
	def __init__(self, plant_string, **kwargs):

		# if students are given they have to be sorted and replace the default children
		if kwargs.has_key("students"):
			self.children = sorted(kwargs["students"])

		# split input string at the newline
		split_plants = plant_string.split()


		for i in xrange(len(split_plants[0])/2):
			# adds a dict entry for the child that we're up to iterating over 2 letters in the first row and two in the second
			self.childrens_plants[self.children[i]] = [self.plant_dict[j] for j in split_plants[0][2*i:2*(i+1)] + split_plants[1][2*i:2*(i+1)]]

	def plants(self, child):
		try:
			return self.childrens_plants[child]
		except KeyError:
			return "This child doesn't exist or doesn't have any plants." 
