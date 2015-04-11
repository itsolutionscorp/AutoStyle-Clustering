# -*- coding:utf-8 -*-
import random
import string

class Name(object):
    _ALNUM = string.letters + string.digits
    _DIGITS = string.digits

    _names = set()

    @staticmethod
    def _randomseq(source, count):
        return [random.choice(source) for i in xrange(count)]
    
    @classmethod
    def generate_name(cls):
        return "".join(
            cls._randomseq(cls._ALNUM, 2) 
            + cls._randomseq(cls._DIGITS, 3)
            )
    
    @classmethod
    def new(cls):
        name = cls.generate_name()
        while name in cls._names:
            name = cls.generate_name()
        cls._names.add(name)
        return name


class Robot(object):
    def __init__(self):
        self.reset()

    def reset(self):
        self.name = Name.new()

                
        
                       
