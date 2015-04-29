import random

class Robot:
    def __init__(self):
        self._get_new_name()

    def reset(self):
        NamePicker.release(self.name)
        self._get_new_name()

    def _get_new_name(self):
        self.name = NamePicker.pick()

    pass


class NamePicker:
    used_names = set()

    @staticmethod
    def pick():
        while True:
            name = str(NameGenerator())
            if name not in NamePicker.used_names:
                NamePicker.used_names.add(name)
                return name

    @staticmethod
    def release(name):
        NamePicker.used_names.remove(name)

    pass


class NameGenerator:
    letters_count = 26
    numbers_count = 1000
    variations_count = letters_count * letters_count * numbers_count

    def __init__(self):
        self.id = random.randrange(NameGenerator.variations_count)

    def __str__(self):
        self.rest = self.id
        return ( self._get_letter() 
                 + self._get_letter() 
                 + self._get_number() )

    def _get_letter(self):
        n = self.rest % NameGenerator.letters_count
        self.rest /= NameGenerator.letters_count
        return chr(n + ord("A"))

    def _get_number(self):
        n = self.rest % NameGenerator.numbers_count
        self.rest /= NameGenerator.numbers_count
        return "{:03d}".format(n)

    pass
