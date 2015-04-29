class Allergies(object):

    def __init__(self, num_allergies):
        allergies_list = [
            ('eggs', 1),
            ('peanuts', 2),
            ('shellfish', 4),
            ('strawberries', 8),
            ('tomatoes', 16),
            ('chocolate', 32),
            ('pollen', 64),
            ('cats', 128)
        ]
        self.list= [];
        for tuple in allergies_list:
            if tuple[1] & num_allergies:
                self.list.append(tuple[0]);

    def is_allergic_to(self, allergy):
        return allergy in self.list
