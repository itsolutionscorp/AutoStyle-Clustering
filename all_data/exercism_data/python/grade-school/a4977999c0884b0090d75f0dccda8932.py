import collections

class School(object):
    def __init__(self, name):
        self.name = name
        self.db = collections.defaultdict(set)

    def add(self, student, room):
        self.db[room].add(student)

    def sort(self):
        return {room: tuple(sorted(students)) 
            for room, students in self.db.iteritems()}
    
    def grade(self, room):
        return self.db[room]
