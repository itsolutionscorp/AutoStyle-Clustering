def bit_is_set(number, n):
    return (number >> n) & 1

class Allergies(object):
    """ Stores and decodes allergy integers. """

    allergies = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]

    def __init__(self, allergy_code):
        self.allergy_code = allergy_code
        self.list = self._list(allergy_code)

    def _list(self, allergy_code):
        allergy_list = []
        for index, allergy in enumerate(self.allergies):
            if bit_is_set(allergy_code, index):
                allergy_list.append(allergy)
        return allergy_list

    def is_allergic_to(self, allergy):
        return bit_is_set(self.allergy_code, self.allergies.index(allergy))
