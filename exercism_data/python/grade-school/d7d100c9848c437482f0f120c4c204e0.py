from collections import defaultdict


class School:
    db = defaultdict(set)
    def __init__(self, name):
        self.name = name
