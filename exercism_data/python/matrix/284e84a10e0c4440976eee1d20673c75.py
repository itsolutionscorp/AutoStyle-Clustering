class Matrix:
    """Builds a matrix from a formatted text string"""

    def __init__(self, raw_data):
        self.rows = [[int(num) for num in row.strip().split(' ')]
                        for row in raw_data.split("\n")]

        self.columns = [list(col) for col in zip(*self.rows)]
