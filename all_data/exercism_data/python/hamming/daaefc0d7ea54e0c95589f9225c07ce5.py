__author__ = 'flux'
def distance(dnaOne, dnaTwo):
    if (len(dnaOne) == len(dnaTwo)):
        error = 0
        for d1,d2 in zip(dnaOne, dnaTwo):
            if (d1 != d2):
                error +=1
    else:
        error = -1

    return error
