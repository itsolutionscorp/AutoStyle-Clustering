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
        if allergy is 'eggs':
            return self.score & Allergies.score[allergy]

        if allergy is 'peanuts':
            return self.score & Allergies.score[allergy]

        if allergy is 'shellfish':
            return self.score & Allergies.score[allergy]

        if allergy is 'strawberries':
            return self.score & Allergies.score[allergy]

        if allergy is 'tomatoes':
            return self.score & Allergies.score[allergy]

        if allergy is 'chocolate':
            return self.score & Allergies.score[allergy]

        if allergy is 'pollen':
            return self.score & Allergies.score[allergy]

        if allergy is 'cats':
            return self.score & Allergies.score[allergy]

        return False

    def list_allergens(self):
        return [a for a in Allergies.allergies if self.is_allergic_to(a)]
