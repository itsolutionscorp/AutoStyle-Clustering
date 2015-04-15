class Allergies:
    scores = [1, 2, 4, 8, 16, 32, 64, 128]
    items = ['eggs', 'peanuts', 'shellfish',
             'strawberries', 'tomatoes', 'chocolate',
             'pollen', 'cats']
    allergens = dict(zip(scores, items))

    def __init__(self, score):
        self.score = score
        self.list = self.build_list()

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def build_list(self):
        results = []
        num = self.score % 256

        for score in reversed(self.scores):
            if num - score >= 0:
                results.append(self.allergens[score])
                num = num - score
        return list(reversed(results))
