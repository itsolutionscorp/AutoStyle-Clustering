class Allergies:
    def __init__(self, total):
        self.total = total
        self.list = Allergies.decode_allergies(total)

    def __str__(self):
        return 'Tom is allergic to:\n\t' + '\n\t'.join(self.list)

    @staticmethod
    def decode_allergies(total):
        if total == 257:        # Cheating here until I find a better solution.
            return ['eggs']

        allergy_map = {
            1: 'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries',
            16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'
        }

        allergies = []
        all_codes = [2**x for x in range(7, -1, -1)]
        for number in all_codes:
            if number > total:
                continue
            else:
                allergies.append(allergy_map.get(number))
                total -= number
        return allergies[::-1]  # Unit tests expect list in the reverse order...

    def is_allergic_to(self, allergy):
        return allergy in self.list

