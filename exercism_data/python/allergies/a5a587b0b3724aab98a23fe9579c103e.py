class Allergies:
    ALLERGY_MAPPING = {1:'eggs',2:'peanuts',4:'shellfish',8:'strawberries',
                       16:'tomatoes',32:'chocolate',64:'pollen',128:'cats'}

    def __init__(self, score):
        self.score = score
        self.list = []
        self.calculate_allergies()

    def calculate_allergies(self):
        for allergy_code in sorted(self.ALLERGY_MAPPING.keys()):
            if self.score & allergy_code:
                self.list.append(self.ALLERGY_MAPPING[allergy_code])

    def is_allergic_to(self, allergen):
        return isinstance(allergen,basestring) and allergen in self.list
