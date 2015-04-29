import random
from string import uppercase, digits


class Robot(object):
    def __init__(self):
        self._name = self._gen_name()

    def _gen_name(self):
        return ''.join(
            [random.choice(uppercase) for _ in range(2)] +
            [random.choice(digits) for _ in range(3)]
        )

    @property
    def name(self):
        if not self._name:
            self._name = self._gen_name()
        return self._name

    def reset(self):
        self._name = None
