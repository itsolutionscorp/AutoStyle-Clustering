import string
import random

""" The test for this suite isn't capturing
collision detection at all. How can that be fixed?"""


class Robot(object):
    """A class that names robots."""

    # Class variable - will be referenced by
    # every instance of Robot
    past_names = set()

    def __init__(self):
        self.name = self.generate_name()

    def generate_name(self):
        letter_list = sample_with_replacement(string.ascii_uppercase, 2)
        number_list = sample_with_replacement(string.digits, 3)
        return "".join(letter_list + number_list)

    def reset(self):
        Robot.past_names.add(self.name)
        while self.name in Robot.past_names:
            self.name = self.generate_name()


def sample_with_replacement(population, k):
    return [random.choice(population) for i in range(k)]
