from random import choice
from threading import Lock

class Robot:
    def __init__(self):
        self.name = Robot.generate_name()

    def reset(self):
        self.name = Robot.generate_name()

    names_used = set([])
    names_used_lock = Lock()

    def generate_name():
        def gen():
            ALPHABETS = list(map(chr, range(ord('A'), ord('Z') + 1)))
            NUMBERS = list(map(chr, range(ord('0'), ord('9') + 1)))
            DOMAIN = [ALPHABETS] * 2 + [NUMBERS] * 3
            return ''.join([choice(x) for x in DOMAIN])
        name = gen()
        with Robot.names_used_lock:
            while name in Robot.names_used:
                name = gen()
            Robot.names_used.add(name)
        return name
    
