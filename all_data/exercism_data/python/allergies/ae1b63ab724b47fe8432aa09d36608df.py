class Allergies:

    def __init__(self, score):
        self.d = {1: 'eggs', 2: 'peanuts', 4: 'shellfish', 8: 'strawberries',
                  16: 'tomatoes', 32: 'chocolate', 64: 'pollen', 128: 'cats'}

        self.score = score
        self.list = []
        self.find_allergens()

    def is_allergic_to(self, allergen):
        if allergen in self.list:
            return True
        else:
            return False

    def find_allergens(self):
        binary_allergy_score = self.binary(self.score)
        decomposed_allergy_score = self.decimal(int(binary_allergy_score))
        self.list = [
            self.d.get(i) for i in decomposed_allergy_score if self.d.get(i) is not None]

    def binary(self, number):
        ans = ''
        if number > 0:
            while number > 1:
                number, remainder = divmod(number, 2)
                ans = str(remainder) + ans
            ans = str(1) + ans
            return ans
        else:
            return str(0)

    def decimal(self, number):
        temp = []
        num_len = len(str(number))
        for i in range(num_len):
            number, remainder = divmod(number, 10)
            remainder = remainder * 2 ** i
            temp.append(remainder)
        ans = [i for i in temp if i is not 0]
        return ans
