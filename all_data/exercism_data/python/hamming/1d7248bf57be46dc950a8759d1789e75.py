from math import fabs

def hamming(a, b):
    count = 0;
    piir = len(a)
    if len(b) < piir:
        piir = len(b)
    for i in range (piir):
        if a[i] != b[i]:
            count+=1
    count += fabs(len(b) - len(a))
    return count
