class Allergies(object):

    allergies = ["eggs", "peanuts", "shellfish", 
                "strawberries", "tomatoes", "chocolate", 
                "pollen", "cats"]

    scores = [1, 2, 4, 8, 16, 32, 64, 128]
    score = dict(zip(allergies, scores))

    def __init__(self, score):
        self.score = score
        self.list = self.list_allergens()

    def is_allergic_to(self, allergy):
        return self.score & Allergies.score[allergy]

    def list_allergens(self):
        return [a for a in Allergies.allergies if self.is_allergic_to(a)]
