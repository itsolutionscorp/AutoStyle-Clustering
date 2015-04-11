#!/usr/bin/python


class Matrix:
    def __init__(self, as_string):
        self.rows = []
        for row_as_string in as_string.split("\n"):
            self.rows.append(map((lambda s: int(s)), row_as_string.strip().split(" ")))

        self.columns = []
        if len(self.rows) > 0:
            for i in range(len(self.rows[0])):
                self.columns.append(map((lambda x: x[i]), self.rows))
