class AllergiesTests:

    all_allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, allergy_val):
        self.allergy_val = allergy_val
        self.ALLERGIC_TO = get_allergens(self.allergy_val, self.all_allergens)

    def is_allergic_to(self, check_value):
        for checker in self.ALLERGIC_TO:
            if checker == check_value:
                return True
        return False


def get_allergens(allergy_val, all_allergens):
    return_list = []
    if allergy_val > 0:
        for i in range(len(all_allergens)-1, -1, -1):
            if allergy_val >= 2 ** i:
                return_list.append(all_allergens[i])
                allergy_val -= 2 ** i
    return return_list
