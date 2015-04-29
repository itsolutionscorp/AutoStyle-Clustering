#
# Counts discrepancies in 2 separate strings, and returns that count
#


def distance(str1, str2):
    ham = 0
    for x in range(len(str1)):
        if not str1[x] == str2[x]:
            ham += 1
    return ham
