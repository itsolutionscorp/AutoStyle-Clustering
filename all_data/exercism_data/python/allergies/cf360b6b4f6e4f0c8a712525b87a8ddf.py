class Allergies(object):
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.allergies = [  'eggs',
                            'peanuts',
                            'shellfish',
                            'strawberries',
                            'tomatoes',
                            'chocolate',
                            'pollen',
                            'cats'
        ]
        self.list = self.get_list()

    def is_allergic_to(self, allergy):
        return self.allergy_score & (2 ** self.allergies.index(allergy))

    def get_list(self):
        list_of_allergies = []
        for allergy in self.allergies:
            if self.is_allergic_to(allergy):
                list_of_allergies.append(allergy)
        return list_of_allergies
