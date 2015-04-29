class Allergies(object):

    LIST = ["eggs", "peanuts", "shellfish", "strawberries",
            "tomatoes", "chocolate", "pollen", "cats"]

    def __init__(self, score):
        self.binary_score = format(score, "08b")[::-1]
        self._list = self._make_allergies_list()

    def _make_allergies_list(self):
        return [allergy for (allergy, bit) in
                zip(Allergies.LIST, self.binary_score) if bit == "1"]

    @property
    def list(self):
        return self._list

    def is_allergic_to(self, allergy):
        return allergy in self._list
