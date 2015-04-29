from itertools import chain

def saddle_points(mtx):
    if not test_matrix(mtx):
        raise ValueError

    if mtx == []:
        return set()

    # Creates a list of indexes of the max point in each row
    list1 = [(x,y) if col == max(row) else None for x, row in enumerate(mtx) for y, col in enumerate(row)]

    chunk = chunks(list(chain.from_iterable(zip(*mtx))), len(mtx))
    mtxT = [x for x in chunk]

    # Creates a list of indexes of the min point in each column
    list2 = [(y,x) if col == min(row) else None for x, row in enumerate(mtxT) for y, col in enumerate(row)]

    # Find the dubplicate points which means intersecting conditional
    return set([x for x in list2 if x in list1 and x != None])


def test_matrix(mtx):
    for row in mtx:
        if len(row) != len(mtx[0]):
            return False
    return True


def chunks(lst, nsize):
    for i in xrange(0, len(lst), nsize):
        yield lst[i:i+nsize]
