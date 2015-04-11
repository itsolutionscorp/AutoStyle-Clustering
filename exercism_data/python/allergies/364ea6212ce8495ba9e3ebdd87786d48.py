__author__ = 'agupt15'

import bisect


class Allergies:
    def __init__(self, num):
        ALLERGY_MAP = [(1, 'eggs'), (2, 'peanuts'), (4, 'shellfish'), (8, 'strawberries'), (16, 'tomatoes'),
                       (32, 'chocolate'), (64, 'pollen'), (128, 'cats')]
        # Sort for bisect to work.
        ALLERGY_MAP.sort(key=lambda r: r[0])

        self.num_allergies = num
        self.list = list()

        scores = []
        names = []
        for score, name in ALLERGY_MAP:
            scores.append(score)
            names.append(name)

        # Resursive call.
        def add_allergy(n):
            subset_idx = bisect.bisect(scores, n)
            if subset_idx > 0:
                self.list.append(names[subset_idx - 1])
                n -= scores[subset_idx - 1]
            else:
                return None
            add_allergy(n)

        add_allergy(num)

    def is_allergic_to(self, allergy):
        return allergy in self.list
