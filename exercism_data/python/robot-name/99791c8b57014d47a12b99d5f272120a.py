__author__ = 'tracyrohlin'

import string, random

class Robot:
    def __init__(self):
        self.name = self.robot_name()
        self.name_list = []


    def robot_name(self):
        alphabet = string.ascii_uppercase
        digits = string.digits

        first_two = [random.choice(alphabet)]
        first_two.append(random.choice(alphabet))

        last_bits = []
        i = 0
        while i < 3:
            last_bits.append(random.choice(digits))
            i+=1
        total_join = first_two + last_bits
        self.name = "".join(total_join)
        return self.name

    def reset(self):
        if self.name not in self.name_list:
            self.name = self.robot_name()

robot = Robot()
print robot.name
