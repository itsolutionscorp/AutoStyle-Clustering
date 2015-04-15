class Matrix:
    """Functions for handling matrices"""

    def __init__(self, raw_data):
        self.rows = []
        for row in raw_data.split("\n"):
            final_row = []
            for num in row.strip().split(' '):
                final_row.append(int(num))
            self.rows.append(final_row)

        self.columns = []
        for i, row in enumerate(self.rows):
            for j, cell in enumerate(row):
                try:
                    self.columns[j].append(cell)
                except IndexError:
                    self.columns.append([cell])
