"""A school."""

from collections import defaultdict, OrderedDict


class School(object):
    """A school."""

    def __init__(self, name):
        self.name = name
        self._db = defaultdict(set)

    @property
    def db(self):
        """Get a copy of the school's internal DB."""
        return dict(self._db)

    def add(self, student, grade):
        """Add ``student`` to the roster for ``grade``."""
        self._db[grade].add(student)

    def grade(self, grade):
        """Return a list of all students enrolled in ``grade``."""
        return self._db[grade]

    def sort(self):
        """Return an ordered dictionary of all students in all grades."""
        ordered = OrderedDict()
        for grade in sorted(self._db):
            students = sorted(self._db[grade])
            ordered[grade] = tuple(students)
        return ordered
