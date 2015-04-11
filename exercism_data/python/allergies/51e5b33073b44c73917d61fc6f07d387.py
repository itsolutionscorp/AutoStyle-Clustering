class Allergies():

    item_scores = [('eggs', 1), ('peanuts', 2), ('shellfish', 4), ('strawberries', 8),
    ('tomatoes', 16), ('chocolate', 32), ('pollen', 64), ('cats', 128)]

    def __init__(self, score):
        self.list = [item for item, item_score in self.item_scores if score & item_score]


    def is_allergic_to(self, food_item):
        return food_item in self.list
