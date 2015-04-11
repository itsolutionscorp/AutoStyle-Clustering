#!/usr/bin/python3


def column(num, lst_of_lst):
    """
    Returns column from matrix (list of lists) as a list.
    """
    try:
        return [row[num] for row in lst_of_lst]
    except IndexError:
        raise ValueError('Provided matrix is irregular.')


def saddle_points(matrix):
    """
    Returns all saddle points in given matrix (list of lists).
    """
    points = set()
    for ridx, row in enumerate(matrix):
        for cidx, element in enumerate(row):
            if element == max(row) and element == min(column(cidx, matrix)):
                points.add((ridx, cidx))
    return points


if __name__ == "__main__":
    pass
