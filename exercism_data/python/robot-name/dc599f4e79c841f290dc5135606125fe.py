#!/usr/bin/env python

import random
import string

class Robot:
    existing_robots = []
    
    def __init__(self):
        self.existing_robots.append(self)
        self.name = None
        self.reset()
    
    def reset(self):
        while True:
            letters = "".join(random.sample(string.uppercase, 2))
            numbers = random.randint(0, 999)
            name = letters + "{0:03}".format(numbers)
            if name not in [robot.name for robot in Robot.existing_robots]:
                self.name = name
                return
