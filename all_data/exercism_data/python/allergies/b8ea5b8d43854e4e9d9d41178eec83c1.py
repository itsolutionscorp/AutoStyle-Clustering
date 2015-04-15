allergens = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()


def _allergens_for(code):
    def present_in_code(n): return code & (1 << n) != 0
    return [x for i, x in enumerate(allergens) if present_in_code(i)]


class Allergies:
    def __init__(self, code):
        self.list = _allergens_for(code)

    def is_allergic_to(self, item):
        return item in self.list
