class Allergies():
    def __init__(self, score):
        self.item_dict = {
            128: 'cats',
            64: 'pollen',
            32: 'chocolate',
            16: 'tomatoes',
            8: 'strawberries',
            4: 'shellfish',
            2: 'peanuts',
            1: 'eggs'
        }
        self.score = score
        self.list = []
        self.calculate_allergic_list()

    def calculate_allergic_list(self):
        temp_score = self.score
        temp_score %= 256
        for key in reversed(sorted(self.item_dict)):
            if temp_score >= key:
                temp_score -= key
                self.list.insert(0, self.item_dict[key])

    def is_allergic_to(self, item):
        if item in self.list:
            return True
        else:
            return False
