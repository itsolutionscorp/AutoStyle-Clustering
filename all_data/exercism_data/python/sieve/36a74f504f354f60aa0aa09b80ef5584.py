def sieve(max):
    mattrix = [i for i in range(2,max+1)]
    index,stop = 0,len(mattrix)

    while index<stop:
        act = mattrix[index]
        mattrix = [mattrix[x] for x in range(0,stop) if mattrix[x]%act!=0 or mattrix[x]==act]
        stop = len(mattrix)
        index += 1

    return mattrix
