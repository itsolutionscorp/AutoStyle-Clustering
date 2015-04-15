#!/usr/bin/env python

class Luhn(object):
    
    def __init__(self, n):
        self.n = n
        self.n_str = self.str_n()
    
    def create(self, num):
        if not self.is_valid():
            self.n_str.append('0')
            if not self.is_valid():
                self.n_str[-1] = 10 - self.checksum()
        
        return int(''.join(i for i in self.n_str))
        
    def addends(self):
        n_list = [int(i) for i in self.n_str]
        length = len(n_list) + 1
        
        for i in range(-2, -length, -2):
            try:
                n_list[i] *= 2
                if n_list[i] >= 10:
                    n_list[i] -= 9
            except IndexError:
                pass
                
        return n_list
            
    def is_valid(self):
        return self.sum_n() % 10 == 0
        
    def checksum(self):
        return self.sum_n() % 10
        
    def sum_n(self):
        return sum(self.addends())
        
    def str_n(self):
        return list(str(self.n))

if __name__ == '__main__':
    print Luhn(201773).checksum()
