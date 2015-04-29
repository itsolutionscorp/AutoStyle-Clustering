import random
import string


class Robot(object):

    """A robot with a unique random name."""

    LETTERS = 2
    DIGITS = 3

    existing_robots = []

    def __init__(self):
        """Create (and boot up) a new robot with a random name."""
        self.existing_robots.append(self)
        self.name = None
        self.reset()

    def generate_name(self):
        """Generate a new random robot name."""
        letters = "".join(random.sample(string.uppercase, self.LETTERS))
        numbers = random.randint(0, 10 ** self.DIGITS - 1)
        return letters + "{0:0{1}}".format(numbers, self.DIGITS)

    def reset(self):
        """Reset robot's name."""
        while True:
            name = self.generate_name()
            if name not in [robot.name for robot in Robot.existing_robots]:
                self.name = name
                return
