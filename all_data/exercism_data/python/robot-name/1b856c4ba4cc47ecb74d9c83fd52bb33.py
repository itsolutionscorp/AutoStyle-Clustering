'''exer robot naming'''

import random
import string

class Robot(object):
    '''the robot class'''

    def __init__(self):
        '''init code'''
        self._name = ''

    def reset(self):
        '''reset the robot and make a new name'''
        self._name = ''

    @property
    def name(self):
        '''if ._name set, return else create a new name'''
        if not self._name:
            # make name = 'aannn'
            # two upper alpha
            self._name = random.sample(string.ascii_uppercase, 2)
            # + 3 random int
            self._name += [str(random.randint(0, 9)) for _ in range(3)]
            # returned as a string
            self._name = ''.join(self._name)

        return self._name
