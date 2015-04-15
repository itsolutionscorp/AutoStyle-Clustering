class Allergies():
    ALLERGIES = (
        ('eggs', 1), ('peanuts', 2), ('shellfish', 4), ('strawberries', 8),
        ('tomatoes', 16), ('chocolate', 32), ('pollen', 64), ('cats', 128)
    )
    def __init__(self, num):
        self.list = [x for x,y in Allergies.ALLERGIES if num & y]

    def is_allergic_to(self, x):
        return x in self.list
