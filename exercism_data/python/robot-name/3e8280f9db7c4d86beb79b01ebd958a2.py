from __future__ import division

class Robot:
    name_counter = 0

    def __init__(self):
        self.reset()

    def reset(self):
        self.name = Robot.gen_name()

    @staticmethod
    def gen_name():
        num_part = Robot.name_counter % 1000
        char_part = Robot.name_counter // 1000

        Robot.name_counter += 1

        char1 = chr(ord('A') + (char_part // 26) % 26)
        char2 = chr(ord('A') + char_part % 26)

        return "%c%c%03d"%(char1, char2, num_part)
