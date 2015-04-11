""" module doc string
"""


class Allergies(object):
    """ class string
    """
    __allergens = {1 << exponent: allergen
                   for exponent, allergen in enumerate(
                       ("eggs peanuts shellfish strawberries tomatoes "
                        "chocolate pollen cats").split())}

    def __init__(self, i):
        """ func doc string
        """
        self.list = [self.__allergens[1 << j]
                     for j in range(len(self.__allergens))
                     if i & (1 << j)]

    def is_allergic_to(self, allergie):
        """ func doc string
        """
        return allergie in self.list
