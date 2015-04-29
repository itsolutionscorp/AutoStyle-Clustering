class Allergies():
    def __init__(self, n_allergies):
        self.n_allergies = n_allergies
        self.food_list = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        self.list = []
        for i in xrange(len(self.food_list)):
            if n_allergies & 2**i: 
                self.list.append(self.food_list[i])
        
        
    def is_allergic_to(self, food):
        return food in self.list
