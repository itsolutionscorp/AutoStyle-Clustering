import operator

allergens = {
    'eggs': 1,
    'peanuts': 2,
    'shellfish': 4,
    'strawberries': 8,
    'tomatoes': 16,
    'chocolate': 32,
    'pollen': 64,
    'cats': 128
}

sorted_allergens = []
for key in sorted(allergens.items(), key=operator.itemgetter(1), reverse=True):
    sorted_allergens += key

for word in sorted_allergens:
    if type(word) != str:
        sorted_allergens.remove(word)


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
                self.list.append(sorted_allergens[i])
        self.list.reverse()

    def is_allergic_to(self, allergen):
        return self.list.__contains__(allergen)
