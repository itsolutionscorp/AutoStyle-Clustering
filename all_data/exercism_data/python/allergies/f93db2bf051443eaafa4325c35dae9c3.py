class Allergies:
    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.list = self.make_list(allergy_score)

    def make_list(self, allergy_score):
        binary_list = [int(x) for x in bin(allergy_score)[2:]]
        possible_allergies = [
                'cats',
                'pollen',
                'chocolate',
                'tomatoes',
                'strawberries',
                'shellfish',
                'peanuts',
                'eggs'
                ]
        final_list = []
        while binary_list:
            try:
                binary_digit = binary_list.pop()
                allergy = possible_allergies.pop()
                if binary_digit:
                    final_list.append(allergy)
            except IndexError:
                break
        return final_list

    def is_allergic_to(self, test_for_allergy):
        return test_for_allergy in self.list
