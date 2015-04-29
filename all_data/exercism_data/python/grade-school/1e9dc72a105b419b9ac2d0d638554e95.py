"""A school."""

from collections import defaultdict, OrderedDict
import copy


class School(object):
    """A school."""

    def __init__(self, name):
        self.name = name
        self._db = defaultdict(set)

    def __repr__(self):
        cls = self.__class__
        return "<{!s}.{!s} {!s} name={!r} _db={!r}>".format(
            cls.__module__, cls.__name__, hex(id(self)), self.name, self._db)

    @property
    def db(self):
        """Get a copy of the school's internal DB."""
        return copy.deepcopy(self._db)

    def add(self, student, grade):
        """Add ``student`` to the roster for ``grade``."""
        self._db[grade].add(student)

    def grade(self, grade):
        """Return a copy of the roster for ``grade``."""
        return set(self._db[grade]) if grade in self._db else set()

    def sort(self):
        """Return an ordered dictionary of all students in all grades."""
        ordered = OrderedDict()
        for grade in sorted(self._db):
            students = sorted(self._db[grade])
            ordered[grade] = tuple(students)
        return ordered
