class Matrix:
    def __init__(self, rows_string):
        self.rows = [[int(int_string) for int_string in row.split()] for row in rows_string.splitlines()]
