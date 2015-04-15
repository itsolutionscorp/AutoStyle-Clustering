from collections import defaultdict


class School(object):

    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, year):
        self.db[year].add(student)

    def grade(self, year):
        return self.db[year]

    def sort(self):
        sorted_dict = sorted(self.db.iteritems(), key=lambda (year, student): year)
        return {entry[0]: tuple(entry[1]) for entry in sorted_dict}
