from random import randrange as rr

class Robot:
    
    def __init__(self):
        n = ""
        for i in range(2):
            n += chr(rr(ord("A"),ord("Z")+1,1))
        for i in range(3):
            n += str(rr(0,10,1))
        self.name = n
        
    def reset(self):
        n = ""
        for i in range(2):
            n += chr(rr(ord("A"),ord("Z")+1,1))
        for i in range(3):
            n += str(rr(0,10,1))
        self.name = n
