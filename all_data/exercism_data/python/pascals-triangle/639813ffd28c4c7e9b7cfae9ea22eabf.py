def triangle(n):
    if n >= 0:
        tr = [[1]]
        for i in xrange(1, n+1):
            prev = tr[i-1]
            row = [0 for n in xrange(len(prev) + 1)]
            for i, n in enumerate(row):
                if i > 0:
                    row[i]  = row[i] + prev[i-1]
                if i < len(prev):
                    row[i]  = row[i] + prev[i]
            tr.append(row)
        return [" ".join(str(n) for n in row) for row in tr]

def row(n):
    return triangle(n)[n]

def is_triangle(tri):
    return tri == triangle(len(tri) - 1)
