class Allergies(object):
    def __init__(self, n):
        self.list = []
        for allergy, number in [
                    ('eggs', 1),
                    ('peanuts', 2),
                    ('shellfish', 4),
                    ('strawberries', 8),
                    ('tomatoes', 16),
                    ('chocolate', 32),
                    ('pollen', 64),
                    ('cats', 128),
                ]:
            if n & number:
                self.list.append(allergy)
    def is_allergic_to(self, stuff):
        return stuff in self.list
    
