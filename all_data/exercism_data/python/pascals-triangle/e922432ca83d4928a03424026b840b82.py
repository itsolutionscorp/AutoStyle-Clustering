def triangle(n):
    rows = list()
    last = [0]
    i = 0
    while i <= n:
        inner = _build_row(i, last)
        rows.append(inner)
        last = inner
        i += 1
    return list([" ".join(str(x) for x in row) for row in rows])

def row(n):
    return triangle(n)[n]

def is_triangle(test_data):
    actual = triangle(len(test_data) - 1)
    difference = set(actual).difference(test_data)
    return len(difference) is 0



def _build_row(i, last):
    row = list()
    for j in range(0, i + 1):
        row.append(_calculate_element(i, j, last))
    return row

def _calculate_element(i, j, last):
    if j is 0 or j is i:
        return 1
    else:
        return (last[j] + last[j - 1])
