allergens = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']


class Allergies(object):

    list = []

    def __init__(self, score):
        self.list = []
        if score >= 256:
            score -= 256
        binary_list = []
        binary_list += bin(score)[2:]
        for i in range(8 - len(binary_list)):
            binary_list.insert(0, 0)
        for i in range(len(binary_list)):
            if binary_list[i] == '1':
                self.list.append(allergens[i])
        self.list.reverse()

    def is_allergic_to(self, allergen):
        return self.list.__contains__(allergen)
