import random

class Robot(object):
    previous_names = set()

    def __init__(self):
        self.reset()

    def __getattr__(self, name):
        if name != "name":
            return super(Robot, self).__getattr__(name)

        if self._name is None:
            self.__generate_name()
        return self._name

    def __generate_name(self):
        self._name = \
            chr(random.randint(65, 90)) + \
            chr(random.randint(65, 90)) + \
            str(random.randint(0, 9)) + \
            str(random.randint(0, 9)) + \
            str(random.randint(0, 9))

        if self._name in Robot.previous_names:
            self.__generate_name()
        else:
            Robot.previous_names.add(self._name)

    def reset(self):
        self._name = None
