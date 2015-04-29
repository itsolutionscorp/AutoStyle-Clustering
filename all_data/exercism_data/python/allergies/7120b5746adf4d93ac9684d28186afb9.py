possible = ['eggs',
            'peanuts',
            'shellfish',
            'strawberries',
            'tomatoes',
            'chocolate',
            'pollen',
            'cats']

class Allergies(object):
    list = []

    def __init__(self, score):
        self.list = get_allergies(score)

    def is_allergic_to(self, allergy):
        return allergy in self.list

def get_allergies(score):
    score %= 2 ** len(possible)
    allergies = []
    for i in range(len(possible) - 1, -1, -1):
        if score >= 2 ** i:
            allergies.append(possible[i])
            score -= 2 ** i
    allergies.reverse()
    return allergies
