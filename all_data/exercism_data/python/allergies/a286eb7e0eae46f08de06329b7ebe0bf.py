class Allergies:

    allergens = {
           1 : 'eggs',
           2 : 'peanuts',
           4 : 'shellfish',
           8 : 'strawberries',
          16 : 'tomatoes',
          32 : 'chocolate',
          64 : 'pollen',
         128 : 'cats',
    }

    def __init__(self, score):
        self.score = score
        self.set_list()

    def is_allergic_to(self, allergen):
        return allergen in self.list

    def set_list(self):
        total_score = self.score
        self.list = []
        for score in sorted(self.allergens, reverse=True):
            if score <= total_score:
                self.list.insert(0, self.allergens[score])
                total_score -= score

        if total_score:
            self.list = ['eggs']
