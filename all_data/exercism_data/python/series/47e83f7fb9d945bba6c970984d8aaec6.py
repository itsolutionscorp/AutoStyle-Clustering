#-------------------------------------------------------------------------------
# Name:        mcface3000
# Purpose:      Headache induction
#-------------------------------------------------------------------------------
def slices(number, size):
    start = 0
    new = []
    test = []
    for i in number:
        test.append(int(i))
    if size == 1:
        for i in test:
            new.append([i])
        return new
    if size == 0:
        raise ValueError('Size is too small')
    if size > len(number):
        raise ValueError('Size is too large')
    while test[start:start+size] != test[-size +1:]:
        new.append(test[start:start+size])
        start += 1
    return new
