"""Pascal's triangle."""

from itertools import imap, islice, izip


def triangle(n):
    """Return Pascal's triangle up to row n (zero-based)."""
    return list(islice(rows_str(), n + 1))


def is_triangle(tri):
    """Return true if Pascal's triangle."""
    return all(r1 == r2 for r1, r2 in izip(tri, rows_str()))


def row(n):
    """Return row n (zero-based) of Pascal's triangle."""
    r = next(islice(rows(), n, n + 1))
    return " ".join(imap(str, r))


def rows_str():
    """Generate the rows (str) of Pascal's triangle."""
    for r in rows():
        yield " ".join(imap(str, r))


def rows():
    """Generate the rows (list of int) of Pascal's triangle."""
    yield [1]
    prev_row = [1]
    while True:
        prev_row_len = len(prev_row)
        curr_row = [
            (prev_row[i - 1] if i - 1 >= 0 else 0) +
            (prev_row[i] if i < prev_row_len else 0)
            for i in xrange(prev_row_len + 1)
        ]
        yield curr_row
        prev_row = curr_row
