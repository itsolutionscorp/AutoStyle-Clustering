class Allergies:

    def is_allergic_to(fooditem):
        return bool(self._pa & fooditem)

    def __init__(self, person_allergies):
        self._pa = person_allergies
        self._fa = ("eggs", "peanuts", "shellfish", "strawberries",
                    "tomatoes", "chocolate", "pollen", "cats")
        result = []
        for food in self._fa:
            if self._pa & 2**self._fa.index(food):
                result.append(food)
        return result
