#!/usr/bin/python
class Allergies():
    def __init__(self, score):
        self.allergies_tested = "eggs peanuts shellfish strawberries tomatoes chocolate pollen cats".split()
        self.list = [x[1] for x in filter(lambda x: x[0] == "1",
                                          zip(list("{:08b}".format(score)[::-1]),
                                              self.allergies_tested))]
                        
    def is_allergic_to(self, allergy):
        return allergy in self.list
