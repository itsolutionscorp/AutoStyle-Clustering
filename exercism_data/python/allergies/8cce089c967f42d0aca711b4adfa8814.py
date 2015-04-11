class Allergies:
    ALLERGIES = [
        'eggs',
        'peanuts',
        'shellfish',
        'strawberries',
        'tomatoes',
        'chocolate',
        'pollen',
        'cats'
    ]

    def __init__(self, score):
        self.score = int(score)
        self.list = self.__determine_allergies()

    def is_allergic_to(self, allergy):
        i = self.ALLERGIES.index(allergy)
        return self.__is_in_list(i)

    def __determine_allergies(self):
        return [allergy for i, allergy in enumerate(self.ALLERGIES) if self.__is_in_list(i)]

    def __is_in_list(self, i):
        return self.score & (2 ** i) != 0
