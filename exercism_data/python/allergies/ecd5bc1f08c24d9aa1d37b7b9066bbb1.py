class Allergies(object):
    allergy_type = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']

    def __init__(self, score):
        self.list = []
        self.score = format(score, '#10b')
        count = 0
        for digit in self.score[-8:]:
            if digit == '1':
                self.list.insert(0, self.allergy_type[count])
            count += 1

    def is_allergic_to(self, allergy):
        return allergy in self.list
