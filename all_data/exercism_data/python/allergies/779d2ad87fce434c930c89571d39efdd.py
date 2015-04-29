class Allergies:
    food_allergies = ("eggs", "peanuts", "shellfish", "strawberries",
                      "tomatoes", "chocolate", "pollen", "cats")

    def is_allergic_to(self, fooditem):
        return bool(self.person_allergies & 2**Allergies.food_allergies.index(fooditem))

    def __init__(self, ascore):
        self.person_allergies = ascore
        self.list = []
        for fooditem in Allergies.food_allergies:
            if self.person_allergies & 2**Allergies.food_allergies.index(fooditem):
                self.list.append(fooditem)
        
