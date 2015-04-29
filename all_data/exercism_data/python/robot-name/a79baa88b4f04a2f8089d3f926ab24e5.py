A = ord('A')

class Robot:
    aoff = 0
    noff = 100

    def __init__(self):
        self.name = Robot.next_name()

    def reset(self):
        self.name = Robot.next_name()


    @classmethod
    def next_name(cls):
        a = cls.aoff
        n = cls.noff + 1

        if n >= 1000:
            n = 100
            a += 1
            cls.aoff = a
        cls.noff = n
        result = chr(A + a / 26) + chr(A + a % 26) + str(n) 

        return result
