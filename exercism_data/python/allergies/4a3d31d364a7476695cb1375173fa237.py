class Allergies(object):
    allergies = [
        "eggs",
        "peanuts",
        "shellfish",
        "strawberries",
        "tomatoes",
        "chocolate",
        "pollen",
        "cats"
    ]

    def __init__(self, score):
        self.list = [
            self.allergies[b]
            for b in xrange(len(self.allergies))
            if score & (1 << b)
        ]

    def is_allergic_to(self, allergy):
        return allergy in self.list
