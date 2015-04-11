#Calculate Hamming distance between two strings, given that they are the same size

#This one liner iterates over both strings and cases the boolean of whether the
#characters at the same position are different, then sums this list.


def distance(str1,str2):
    return sum([int(str1[n]!=str2[n]) for n in range(len(str1))])
