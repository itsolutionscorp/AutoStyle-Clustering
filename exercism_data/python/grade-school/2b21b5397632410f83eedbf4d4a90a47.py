class School(object):
    def __init__(self, name):
        self.db = {}

    def add(self, student, grade):
        """
        Add student to grade
        """
        self.db.setdefault(grade, set())
        self.db[grade].add(student)

    def grade(self, grade):
        """
        Return students in grade
        """
        return self.db.get(grade, set())

    def sort(self):
        """
        Return list of grades along with students each grades
        """
        return [(k, tuple(sorted(v))) for (k, v) in self.db.items()]
