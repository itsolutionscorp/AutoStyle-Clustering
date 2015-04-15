from collections import defaultdict, OrderedDict


class School(object):
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        """Add student to grade."""
        self.db[grade].add(student)

    def grade(self, grade):
        """Get students in grade."""
        return self.db.get(grade, set())

    def sort(self):
        """Sort school by grade ascending and students alphabetically."""
        db_items = self.db.items()
        sorted_items = [(t[0], tuple(sorted(t[1]))) for t in sorted(db_items)]
        return OrderedDict(sorted_items)
