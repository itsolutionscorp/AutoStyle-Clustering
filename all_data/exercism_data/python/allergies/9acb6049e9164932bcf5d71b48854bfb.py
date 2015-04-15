
class Allergies(object):
    def __init__(self, rating):
        self.list = []
        self.allergins = ['eggs', 'peanuts', 'shellfish',
                          'strawberries', 'tomatoes', 'chocolate',
                          'pollen', 'cats']

        self.sorting = {i: v for i, v in enumerate(self.allergins)}

        if rating > 0:
            for i in self.sorting:
                if 2**i == (2**i & rating):
                    self.list.append(self.sorting[i])

    def is_allergic_to(self, allergin):
        return allergin in self.list
