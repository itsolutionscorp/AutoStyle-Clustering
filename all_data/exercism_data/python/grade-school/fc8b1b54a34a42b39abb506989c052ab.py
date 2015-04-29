from collections import defaultdict

class School:
    """Archives student names and grade-levels"""

    def __init__(self, name):
        """Constructs a school"""
        self.name = name
        self.db = defaultdict(set)

    def add(self, name, grade):
        """Adds a student to the db with key grade-level"""
        self.db[grade].add(name)

    def grade(self, grade):
        """Returns list of students in a grade-level"""
        return self.db[grade]

    def sort(self):
        """Returns rosters sorted according to grade-level"""
        return {grade: tuple(sorted(self.db[grade])) for grade in self.db}
