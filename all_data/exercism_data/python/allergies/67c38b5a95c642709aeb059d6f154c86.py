
class Allergies(object):
    def __init__(self, rating):
        self.list = []
        self.allergins = ['eggs', 'peanuts', 'shellfish',
                          'strawberries', 'tomatoes', 'chocolate',
                          'pollen', 'cats']

        if rating > 0:
            self.list = [value for index, value in enumerate(self.allergins) if 2**index & rating]

    def is_allergic_to(self, allergin):
        return allergin in self.list
