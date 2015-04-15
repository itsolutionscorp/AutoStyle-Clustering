import random

previous_names = set()

class Robot(object):
    _name = None

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

        if self._name in previous_names:
            self.__generate_name()
        else:
            previous_names.add(self._name)

    def reset(self):
        self._name = None
