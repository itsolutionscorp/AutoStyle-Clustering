import string,random

class Robot:
    hashes = {}
    def __init__(self):
        self.name_creator()

    def reset(self):
        self.name_creator()

    def name_creator(self):
        while True:
            a = "".join(random.SystemRandom().choice(string.ascii_uppercase) for _ in range(2))
            b = "".join(random.SystemRandom().choice(string.digits) for _ in range(3))
            self.name = a+b
            if self.name not in self.hashes.keys():
                self.hashes[self.name] = 0
                break
        
