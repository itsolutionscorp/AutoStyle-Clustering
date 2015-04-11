# -*- coding: utf-8 -*-


class School():
    """represents a grade school. Provides a minimalist database."""

    def __init__(self,name="Not Named"):
        """Create a minimalist database for students in the grad school"""
        self.name = name
        self.db=dict()

    def add(self, name, grade:int):
        """Add a student to the registry of given grade"""
        self.db.setdefault(grade,set()).add(name)

    def grade(self, grade:int):
        """Returns a set of students in this grade"""
        return self.db.setdefault(grade, set())

    def sort(self):
        """fetch a sorted """
        return {grade:tuple( sorted(self.db[grade]) )
                for grade in self.db.keys() }

def main():
    pass

if __name__ == '__main__':
    main()
