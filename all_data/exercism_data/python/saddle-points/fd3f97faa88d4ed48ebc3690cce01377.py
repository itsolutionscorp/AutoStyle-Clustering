#!/usr/bin/python3


def saddle_points(matrix):
    """
    Returns all saddle points in given matrix (list of lists).
    """
    if not all(len(row) == len(matrix[0]) for row in matrix[1:]):
        raise ValueError('Provided matrix is irregular.')
    columns = [col for col in zip(*matrix)]
    points = set()
    for ridx, row in enumerate(matrix):
        for cidx, element in enumerate(row):
            if element == max(row) and element == min(columns[cidx]):
                points.add((ridx, cidx))
    return points


if __name__ == "__main__":
    pass
