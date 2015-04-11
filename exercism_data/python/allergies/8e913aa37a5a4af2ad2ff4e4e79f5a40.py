class Allergies():

    def __init__(self, value):
        self.score = value % 256
        allergens = [
            'cats',
            'pollen',
            'chocolate',
            'tomatoes',
            'strawberries',
            'shellfish',
            'peanuts',
            'eggs'
        ]
        # Convert the score to a binary string where 0 or 1 indicate being allergic
        binary_score = [int(b) for b in format(self.score, '08b')]
        # Comprehend a list of allergies based on whether each allergen is scored
        self.list = [allergy for allergy, allergic in zip(allergens, binary_score) if allergic ]
        # Reverse the list to match the tests which care about order.
        self.list.reverse()


    def is_allergic_to(self, allergen):
        return allergen in self.list
