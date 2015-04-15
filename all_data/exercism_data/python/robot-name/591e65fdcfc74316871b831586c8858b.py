import random
import string

def _random_letters(n):
    return ''.join(random.choice(string.ascii_letters) for _ in range(n))

def _random_numbers(n):
    return ''.join(random.choice(string.digits) for _ in range(n))


class AllNamesTaken(Exception): pass


class NameGenerator(object):
    NUM_POSSIBLE_NAMES = 52 * 52 * 10 * 10 * 10

    def __init__(self):
        self.assigned_names = set()

    def generate_name(self):
        return _random_letters(2) + _random_numbers(3)

    def generate_fresh_name(self):
        if len(self.assigned_names) == NameGenerator.NUM_POSSIBLE_NAMES:
            raise AllNamesTaken
        while True:
            name = self.generate_name()
            if name not in self.assigned_names:
                self.assigned_names.add(name)
                return name

    def free_up(self, name):
        self.assigned_names.discard(name)


class Robot(object):
    name_generator = NameGenerator()

    def __init__(self):
        self.name = ''
        self.reset()

    def reset(self):
        new_name = Robot.name_generator.generate_fresh_name()
        Robot.name_generator.free_up(self.name)
        self.name = new_name
