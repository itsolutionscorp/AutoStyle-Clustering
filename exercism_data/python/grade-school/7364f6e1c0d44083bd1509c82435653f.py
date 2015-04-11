__author__ = 'banarasitippa'

from collections import OrderedDict

class School():

    def __init__(self,name):
        self.name = name
        self.db = {}

    def add(self,student,grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self,grade = None):

        if grade in self.db:
            return self.db[grade]
        else:
            return set()

#    def sort(self):
#        return OrderedDict(k:self.db[k] for k in sorted(self.db.keys()))
            #(OrderedDict(self.db.items(), key=lambda t: t[0]))
        #iter(sorted(self.db.iteritems()))
