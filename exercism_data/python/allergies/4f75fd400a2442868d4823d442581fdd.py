#!/usr/bin/python
# -*- coding: utf-8 -*-



class Allergies:
    def __init__(self, value):
        # strip bit 8 and above from the input value
        self.value = value % 256
        
        # initiate list of all allergies in reverse, and an empty list
        self.alist = ['eggs','peanuts','shellfish','strawberries', \
            'tomatoes','chocolate','pollen','cats']
        self.list = []
        
        # run through numbers/bitpositions in reverse, divide input 
        # value by the value represented by the bit position.
        # If we get a non-zero divisor, we have an allergy. Add to list.
        # The remainder is the new input value for the test
        
        for number in reversed(range(8)):
            hit,remainder = divmod(self.value, 2**(number))
            if hit:
                self.list.insert(0,self.alist[number])
            self.value = remainder
        
    
    def is_allergic_to(self, thing):
         return thing in self.list
        
