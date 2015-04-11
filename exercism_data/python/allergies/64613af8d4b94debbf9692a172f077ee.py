class Allergies(object):
    allergens = ('eggs peanuts shellfish strawberries tomatoes chocolate pollen cats').split()
   
    def __init__(self, num):
        self.num = num
        self.list = []
        for i in range(len(self.allergens)):
            if self.num & (1 << i):
                self.list.append(self.allergens[i])
                
    def is_allergic_to(self, allergen):
        return allergen in self.list
