import collections


class Allergies(object):

    allergy_digits = collections.OrderedDict([
        ("eggs", 0),
        ("peanuts", 1),
        ("shellfish", 2),
        ("strawberries", 3),
        ("tomatoes", 4),
        ("chocolate", 5),
        ("pollen", 6),
        ("cats", 7),
    ])

    def __init__(self, score):
        self.score = score
        score_bin = bin(self.score)[2:]  # 14 -> 0b1110 -> 1110
        self.score_bin_reversed = score_bin[::-1]

    def is_allergic_to(self, item):
        idx = self.allergy_digits.get(item)
        if len(self.score_bin_reversed) < idx + 1:
            return False
        return self.score_bin_reversed[idx] == "1"

    @property
    def list(self):
        # Returns list of items that cause allergy
        return [k for k, v in self.allergy_digits.items() if self.is_allergic_to(k)]
