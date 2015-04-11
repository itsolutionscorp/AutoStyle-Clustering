class Allergies():

    def __init__(self, allergies_val):
        _all_allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        self.allergies_as_bin = bin(allergies_val).lstrip('0b')
        self.list = []

        a = list(self.allergies_as_bin)
        a.reverse()  # Lowest significant bit first

        for index, allergy in enumerate(a[0:8]):
            if allergy == '1':
                self.list.append(_all_allergies[index])

    def is_allergic_to(self, allergy_str):
        for client_allergy in self.list:
            if client_allergy == allergy_str:
                return True

        return False
