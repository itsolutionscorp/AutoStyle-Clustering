def row(n):
    if n == 0:
        return '1'
    elif n == 1:
        return '1 1'
    else:
        last_row = row(n-1).split(' ')
        ret = list()
        ret.append('1')
        for i in xrange(1, n):
            ret.append(str(int(last_row[i-1]) + int((last_row[i]))))
        ret.append('1')
        return ' '.join(ret)


def triangle(n):
    return [row(i) for i in xrange(n+1)]
    

def is_triangle(lst):
    for i in xrange(len(lst)):
        if lst[i] != row(i):
            return False
    return True
