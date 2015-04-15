class School(object):

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db.keys():
            self.db[grade].add(student)
        else:
            self.db[grade] = set([student])

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        return set()

    def sort(self):
        sorteddb = {}
        for grade, students in self.db.iteritems():
            sortedstudents = sorted(students)
            sorteddb[grade] = tuple(sortedstudents)
        return sorteddb
