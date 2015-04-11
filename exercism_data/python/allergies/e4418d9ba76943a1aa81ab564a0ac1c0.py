class Allergies:
    origList = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = [ allergy for index,allergy in enumerate(self.origList) if (2**index) & self.score]

    def is_allergic_to(self, allergy):
        return allergy in self.list
