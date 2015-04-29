#
# hamming function
#

def distance(strandone,strandtwo):
    size = len(strandone)
    dist = 0
    for i in range(size):
        if strandone[i] != strandtwo[i]:
            dist = dist + 1
    return dist
