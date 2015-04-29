def distance(in1 , in2):
    dist=0
    for x in range(len(in1)):
        if in1[x]!=in2[x]:
            dist+=1
    return dist
