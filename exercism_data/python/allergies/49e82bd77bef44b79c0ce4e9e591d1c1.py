class Allergies:
    def __init__(self, score):
        self._score = score % 256
        self.list = []
        self.possible_allergy_list = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes",
                                      "chocolate", "pollen", "cats"]
        self.create_known_allergies()

    def create_known_allergies(self):
        num = self._score
        a_list = self.possible_allergy_list
        while num:
            if num % 2:
                self.list.append(a_list[0])
            a_list = a_list[1:]
            num /= 2

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        else:
            return False
