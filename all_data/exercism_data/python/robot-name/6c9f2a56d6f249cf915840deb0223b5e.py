from __future__ import division

import random

class Robot:
    name_counter = 0
    names_count = 26*26*1000
    used_name_counters = set()

    def __init__(self):
        self.reset()

    def reset(self):
        self.name = Robot.gen_name()

    @staticmethod
    def gen_name():
        Robot.advance_name_counter()

        num_part = Robot.name_counter % 1000
        char_part = Robot.name_counter // 1000

        char1 = chr(ord('A') + (char_part // 26) % 26)
        char2 = chr(ord('A') + char_part % 26)

        return "%c%c%03d"%(char1, char2, num_part)

    @staticmethod
    def advance_name_counter():
        if len(Robot.used_name_counters) >= Robot.names_count:
            raise RuntimeError("All robot names used")

        Robot.name_counter = random.randint(0, Robot.names_count - 1)

        while Robot.name_counter in Robot.used_name_counters:
            Robot.name_counter += 1

        Robot.used_name_counters.add(Robot.name_counter)
