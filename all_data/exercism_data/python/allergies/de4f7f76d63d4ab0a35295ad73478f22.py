class Allergies(object):

    def __init__(self, score):
        self.foods = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()
        self.list = [self.foods[i] for i in range(len(self.foods)) if score & 1<<i]

    def is_allergic_to(self,food):
        return food in self.list
