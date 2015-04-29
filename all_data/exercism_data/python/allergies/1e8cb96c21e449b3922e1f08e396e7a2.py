class Allergies():

    allergies = ["eggs", "peanuts", "shellfish", "strawberries",
                 "tomatoes", "chocolate", "pollen", "cats"]

    def __init__(self, n):
        self.list = [allergy for (i, allergy) in enumerate(self.allergies)
                     if n & (2 ** i)]

    def is_allergic_to(self, food):
        return food in self.list
