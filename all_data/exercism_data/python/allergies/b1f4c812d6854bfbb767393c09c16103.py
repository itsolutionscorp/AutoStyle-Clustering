class Allergies(object):
    def __init__(self, score):
        # truncate anything beyond
        # 8 least significant bits
        if score >= 256:
            score %= 256

        self.list = []
        allergies = [
            (2**0, "eggs"),
            (2**1, "peanuts"),
            (2**2, "shellfish"),
            (2**3, "strawberries"),
            (2**4, "tomatoes"),
            (2**5, "chocolate"),
            (2**6, "pollen"),
            (2**7, "cats")
        ]
        for i, allergy in reversed(allergies):
            if score >= i:
                score -= i
                self.list.insert(0, allergy)

    def is_allergic_to(self, allergy):
        return allergy in self.list
