"""A minesweeper."""


CORNER, VERTICAL_SIDE, HORIZONTAL_SIDE = ("+", "|", "-")
VACANT, MINE = (" ", "*")


def board(rows):
    """Return the filled form of a minefield."""
    if not is_valid(rows):
        raise ValueError("Invalid board")

    coord = Coordinate(width=len(rows[0]), height=len(rows))
    rows_filled = []
    for y, row in enumerate(rows):
        row_filled = []
        for x, square in enumerate(row):
            if square == VACANT:
                neighbors = coord.neighbors(x, y)
                mines = sum(rows[r][c] == MINE for c, r in neighbors)
                row_filled.append(str(mines) if mines > 0 else VACANT)
            else:
                row_filled.append(square)
        rows_filled.append("".join(row_filled))
    return rows_filled


def is_valid(rows):
    """Return true if a valid representation of a minefield."""
    width, height = len(rows[0]), len(rows)

    # Check if every row has the same length.
    if any(len(row) != width for row in rows):
        return False

    # Check if each square contains a character suitable for its position.
    coord = Coordinate(width, height)
    for y, row in enumerate(rows):
        for x, square in enumerate(row):
            if coord.is_corner(x, y):
                if square != CORNER:
                    return False
            elif coord.is_vertical_side(x, y):
                if square != VERTICAL_SIDE:
                    return False
            elif coord.is_horizontal_side(x, y):
                if square != HORIZONTAL_SIDE:
                    return False
            elif square not in {VACANT, MINE}:
                return False

    return True


class Coordinate(object):
    """A coordinate system."""

    def __init__(self, width, height):
        """Create a coordinate system of a given width and height."""
        x_max, y_max = width - 1, height - 1

        self.width = width
        self.height = height

        self.corners = {(0, 0), (x_max, 0), (0, y_max), (x_max, y_max)}

        self.horizontal_sides = set()
        self.horizontal_sides.update((x, 0) for x in xrange(1, x_max))
        self.horizontal_sides.update((x, y_max) for x in xrange(1, x_max))

        self.vertical_sides = set()
        self.vertical_sides.update((0, y) for y in xrange(1, y_max))
        self.vertical_sides.update((x_max, y) for y in xrange(1, y_max))

    def up(self, x, y):
        """Return the upper coordinate if possible, otherwise None."""
        return (x, y - 1) if y - 1 >= 0 else None

    def down(self, x, y):
        """Return the lower coordinate if possible, otherwise None."""
        return (x, y + 1) if y + 1 < self.height else None

    def left(self, x, y):
        """Return the left coordinate if possible, otherwise None."""
        return (x - 1, y) if x - 1 >= 0 else None

    def right(self, x, y):
        """Return the right coordinate if possible, otherwise None."""
        return (x + 1, y) if x + 1 < self.width else None

    def up_left(self, x, y):
        """Return the upper left coordinate if possible, otherwise None."""
        return (x - 1, y - 1) if x - 1 >= 0 and y - 1 >= 0 else None

    def up_right(self, x, y):
        """Return the upper right coordinate if possible, otherwise None."""
        return (x + 1, y - 1) if x + 1 < self.width and y - 1 >= 0 else None

    def down_left(self, x, y):
        """Return the lower left coordinate if possible, otherwise None."""
        return (x - 1, y + 1) if x - 1 >= 0 and y + 1 < self.height else None

    def down_right(self, x, y):
        """Return the lower right coordinate if possible, otherwise None."""
        return (x + 1, y + 1) if x + 1 < self.width and y + 1 < self.height else None

    def neighbors(self, x, y):
        """Return the neighbors' coordinates."""
        coords = (
            f(x, y)
            for f in (
                self.up, self.down, self.left, self.right,
                self.up_left, self.up_right, self.down_left, self.down_right
            )
        )
        return filter(lambda c: c is not None, coords)

    def is_corner(self, x, y):
        """Return true if a coordinate is on a corner."""
        return (x, y) in self.corners

    def is_vertical_side(self, x, y):
        """Return true if a coordinate is on the vertical side."""
        return (x, y) in self.vertical_sides

    def is_horizontal_side(self, x, y):
        """Return true if a coordinate is on the horizontal side."""
        return (x, y) in self.horizontal_sides
