class Allergies(object):
    def __init__(self, score):
        self.score = score % 256
        self.allergens = ['eggs', 'peanuts', 'shellfish',
                          'strawberries', 'tomatoes', 'chocolate',
                          'pollen', 'cats']
        self.list = self.determine_list()

    def determine_list(self):
        reversed_bin_rep = bin(self.score)[2:][::-1]
        result = []

        for index in range(len(reversed_bin_rep)):
            if reversed_bin_rep[index] == '1':
                result.append(self.allergens[index])

        return result
    
    def is_allergic_to(self, allergen):
        self.determine_list()
        return allergen in self.list
