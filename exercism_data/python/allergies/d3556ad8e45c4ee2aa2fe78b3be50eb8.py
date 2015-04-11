scores = {
    'eggs': 1,
    'peanuts': 2,
    'shellfish': 4,
    'strawberries': 8,
    'tomatoes': 16,
    'chocolate': 32,
    'pollen': 64,
    'cats': 128
}

class Allergies:
    def __init__(self,score):
        self.score = score
        self.list = filter(lambda item: self.is_allergic_to(item),scores)
    def is_allergic_to(self,item):
        return self.score & scores[item] == scores[item]
