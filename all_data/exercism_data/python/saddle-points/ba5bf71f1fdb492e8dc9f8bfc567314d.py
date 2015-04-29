"""Saddle points."""


def saddle_points(rows):
    """Return saddle points."""
    if not is_matrix(rows):
        raise ValueError("irregular matrix")

    rows_max = [max(row) for row in rows]
    columns_min = [min(column) for column in zip(*rows)]
    nrows = len(rows)
    ncolumns = len(rows[0]) if nrows > 0 else 0
    return set(
        (r, c)
        for r in xrange(nrows)
        for c in xrange(ncolumns)
        # If rows_max[r] == columns_min[c], then rows[r][c] == rows_max[r] ==
        # columns_min[c] because columns_min[c] <= rows[r][c] <= rows_max[r].
        if rows_max[r] == columns_min[c]
    )


def is_matrix(rows):
    """Return true if a matrix."""
    # [] represents an empty matrix.
    if not rows:
        return True

    # Check if every row has the same length.
    row_len = len(rows[0])
    if any(len(row) != row_len for row in rows):
        return False

    return True
