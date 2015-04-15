import string
import random


class Robot(object):
    """A class that names robots."""
    def __init__(self):
        self.name = self.generate_name()
        self.past_names = []

    def generate_name(self):
        letter_list = sample_with_replacement(string.ascii_uppercase, 2)
        number_list = sample_with_replacement(string.digits, 3)
        return "".join(letter_list + number_list)

    def reset(self):
        self.past_names.append(self.name)
        while True:
            new_name = self.generate_name()
            if new_name not in self.past_names:
                self.name = new_name
                return None


def sample_with_replacement(population, k):
    return [random.choice(population) for i in range(k)]
