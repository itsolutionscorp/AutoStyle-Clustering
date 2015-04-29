#!/usr/bin/env python3

class Allergies:
    '''
    Takes an allergy score and creates a list of allergens
    is_allergic_to(allergen) will tests whether a specific allergen is
    in the list of allergens
    '''

    def __init__(self, score=0):
        self.score = score

        allergens = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
                     'chocolate', 'pollen', 'cats']

        def convert_to_binary_string(self, score):
            '''
            Converts the score into binary string, reverses it,
            and removes the first two entries.
            '''

            '''
            >>> convert_to_binary_string(255)
            ['1', '1', '1', '1', '1', '1', '1', '1']
            '''

            return list(reversed(bin(score)[2:]))

        def map_score_to_allergens(self):
            '''
            The chosen score scheme increases as 2**N. The score, when
            converted to a binary string, creates an easy lookup table
            for allergens.
            '''

            '''
            >>> map_score_to_allergens()
            [('1', 'eggs'),
             ('1', 'peanuts'),
             ('1', 'shellfish'),
             ('1', 'strawberries'),
             ('1', 'tomatoes'),
             ('1', 'chocolate'),
             ('1', 'pollen'),
             ('1', 'cats')]
            '''

            bin_score = convert_to_binary_string(self, self.score)
            return list(zip(bin_score, allergens))

        allergen_score_list = map_score_to_allergens(self)

        self.list = [entry[1] for entry in allergen_score_list
                     if entry[0] == '1']

    def is_allergic_to(self, allergen):
        '''
        Determines whether an specific allergen is in the list of allergies
        determined from the allergy score
        '''

        return allergen in self.list
