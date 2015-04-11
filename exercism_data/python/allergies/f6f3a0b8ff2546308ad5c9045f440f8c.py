class Allergies(object):
    allergens = ('eggs peanuts shellfish strawberries tomatoes chocolate pollen cats').split()
    mask = [1, 2, 4, 8, 16, 32, 64, 128]
    def __init__(self, num):
        self.num = num
        self.list = []
        i = 0
        while i < len(self.mask):
            if self.num & self.mask[i]:
                self.list.append(self.allergens[i])
            i += 1
                        
    def is_allergic_to(self, allergen):
        for item in self.list:
            if item == allergen:
                return True
        return False
