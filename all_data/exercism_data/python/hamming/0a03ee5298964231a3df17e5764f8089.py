__author__ = 'Cedric Zhuang'

from operator import ne


# came from facerolling's version at
# http://exercism.io/submissions/2682d39b454536ccf081582a
def distance(x, y):
    return sum(map(ne, x, y))

