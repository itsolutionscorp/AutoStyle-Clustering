class Allergies():
    possible_allergies = ["eggs", "peanuts", "shellfish", "strawberries",
                          "tomatoes", "chocolate", "pollen", "cats"]

    def __init__(self, n):
        self.score = n
        self.list = Allergies.list(n)

    def is_allergic_to(self, allergy):
        return allergy in self.list

    @classmethod
    def list(cls, n):
        binary_list = list(bin(n))[2:][::-1] + [0]*len(cls.possible_allergies)
        return [a for i, a in enumerate(cls.possible_allergies)
                if binary_list[i] == '1']
