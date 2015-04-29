# -*- coding:utf-8 -*-
from collections import defaultdict

class School(object):
    def __init__(self, *args, **kw):
        self._db = defaultdict(set)

    def add(self, name, grade):
        self._db[grade].add(name)

    def grade(self, grade):
        return self._db.get(grade, set())

    def sort(self):
        return dict(
            (grade, tuple(sorted(names))) 
            for grade, names in sorted(self._db.iteritems())
            )

    # bonus points : prevent code to directly mutate the db
    @property
    def db(self):
        db = defaultdict(set)
        db.update(
            (grade, set(names))
            for grade, names in self._db.iteritems()
            )
        return db
