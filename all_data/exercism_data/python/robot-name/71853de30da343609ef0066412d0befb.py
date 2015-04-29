import string
import random

Robots = [ ]

def _generate_unique_robot_name():
    while True:
        name = _generate_robot_name()
        if not any(robot for robot in Robots if robot.name == name):
            return name

def _generate_robot_name():
    return "".join(
        [random.choice(string.ascii_uppercase) for i in range(2)] +
        [random.choice(string.digits) for i in range(3)]
    )

class Robot(object):

    name = None

    def __init__(self):
        Robots.append(self)
        self._set_name()

    def reset(self):
        self._set_name()

    def _set_name(self):
        self.name = _generate_unique_robot_name()
