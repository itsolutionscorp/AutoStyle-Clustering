def triangle(n):
    result = [[1]]
    for row in range(n):
        previous = result[-1]
        result.append(
            [1] + map(sum, zip(previous, previous[1:])) + [1]
        )
    return [' '.join(map(str, row)) for row in result]

def is_triangle(rows):
    return rows == triangle(len(rows) - 1)

def row(index):
    return triangle(index)[-1]
