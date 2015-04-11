class Allergies:
    allergies = ("eggs", "peanuts", "shellfish", "strawberries",
                 "tomatoes", "chocolate", "pollen", "cats")

    def is_allergic_to(self, allergy):
        return allergy in self.list

    def __init__(self, ascore):
        self.person_allergies = ascore
        self.list = []
        for allergy in Allergies.allergies:
            if self.person_allergies & 2**Allergies.allergies.index(allergy):
                self.list.append(allergy)
        
