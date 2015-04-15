class Allergies(object):
    
    def __init__(self, allergen_score):
        self.allergen_score = allergen_score
        self.list = self.get_list()


    def get_pairs_of_binary_and_nth_power(b):   
        def get_length_of_binary_without_prefix(b):
            return len(b) - 2

        reversed_binary_string = reversed(b)
        nth_powers = range(get_length_of_binary_without_prefix(b))
        return zip(reversed_binary_string, nth_powers)


    def get_list(self):
        score_as_binary_string = bin(self.allergen_score)
        
        binary_nth = Allergies.get_pairs_of_binary_and_nth_power(
            score_as_binary_string)

        allergen_values = []
        allergen_items = []

        for b, nth in binary_nth:
            if int(b):
                allergen_values.append(2**nth)
        
        for n in allergen_values:
            if n == 1: allergen_items.append('eggs')
            if n == 2: allergen_items.append('peanuts')
            if n == 4: allergen_items.append('shellfish')
            if n == 8: allergen_items.append('strawberries')
            if n == 16: allergen_items.append('tomatoes')
            if n == 32: allergen_items.append('chocolate')
            if n == 64: allergen_items.append('pollen')
            if n == 128: allergen_items.append('cats')

        return allergen_items

    def is_allergic_to(self, allergen):
        return allergen in self.list
