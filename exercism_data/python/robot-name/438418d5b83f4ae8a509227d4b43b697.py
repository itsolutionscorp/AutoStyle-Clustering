"""Beep, bop, boop. I, Robot."""

import random
import string


# store a list of created robot names to ensure uniqueness upon name creation
_robot_names = set()


class Robot(object):
    def __init__(self):
        self._set_unique_name()

    def reset(self):
        self._set_unique_name()

    def _set_unique_name(self):
        """Get random unique name and assign it.
           If unable to get a random unique name raise exception."""
        name = self._get_random_name()
        attempts = 1
        max_attempts = 20

        # Ensure names are unique. If number of attempts is too great bail out.
        while name in _robot_names and attempts < max_attempts:
            name = self._get_random_name()
            attempts += 1

        if attempts >= max_attempts:
            raise RuntimeError("{0} attempts to create name failed."
                               " Too many robots?".format(max_attempts))

        self.name = name
        _robot_names.add(name)

    def _get_random_name(self):
        """Make a random name."""
        name = ''
        letter_count = 2
        digit_count = 3

        for _ in xrange(letter_count):
            name += random.choice(string.ascii_uppercase)
        for _ in xrange(digit_count):
            name += random.choice(string.digits)

        return name
