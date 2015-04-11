class Allergies:

    def __init__(self, score):

        self.score = score % self._get_max_score()
        self.list = self._get_allergies_list()


    all_allergies = ['eggs',
                            'peanuts',
                            'shellfish',
                            'strawberries',
                            'tomatoes',
                            'chocolate',
                            'pollen',
                            'cats']


    def is_allergic_to(self, allergy):

        return allergy in self.list


    def _get_allergies_list(self):

        allergies = []

        for idx, bit in _get_reversed_binary_enum(self.score):
            if bit:
                allergies.append(self.all_allergies[idx])

        return allergies


    def _get_max_score(self):

        return 2**len(self.all_allergies)


def _get_reversed_binary_enum(num):

    return enumerate(reversed([int(x) for x in bin(num)[2:]]))
