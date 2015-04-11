class School(object):

    def __init__(self, name):
        self.name = name
        self.db = {}      

    def add(self, student, grade):
        """Adds a given student to a given grade"""
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, grade):
        """Returns a set of all students in a given grade"""
        if grade in self.db:
            return self.db[grade]
        else:
            return set()

    def sort(self):
        """Returns a dictionary sorted by grade as the keys
        and alphabetical student tuples as the values
        """
        return {key:tuple(sorted(list(value)))
                for key,value in sorted(self.db.items())}
