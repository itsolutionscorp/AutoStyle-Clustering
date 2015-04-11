from enum import Enum, unique
from math import log


class Allergies:

    score = 0

    def __init__(self, score):
        """
        :param int score: The allergy score
        :return:
        """
        self.score = score

    def is_allergic_to(self, allergen):
        """
        :param str allergen:
        :return: bool
        """
        return bool(self.score & getattr(Allergen, allergen).value)

    @property
    def list(self):
        """
        Get the list of allergens for a given allergy score.

        :param int score:
        :return: [str]
        """
        return [allergen.name for allergen in Allergen if self.is_allergic_to(allergen.name)]


@unique
class Allergen(Enum):
    """
    Possible allergens and their related index in the binary string of the allergic score
    """
    eggs = 2**0
    peanuts = 2**1
    shellfish = 2**2
    strawberries = 2**3
    tomatoes = 2**4
    chocolate = 2**5
    pollen = 2**6
    cats = 2**7
