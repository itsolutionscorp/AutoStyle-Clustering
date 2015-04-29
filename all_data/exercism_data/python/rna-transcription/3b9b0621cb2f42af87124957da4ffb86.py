__author__ = 'tracyrohlin'

def to_rna(s):
    s = list(s)

    for num in range(len(s)):
        if s[num] == "G":
            s[num] = "C"
        elif s[num] == "C":
            s[num] = "G"
        elif s[num] == "T":
            s[num] = "A"
        else:
            s[num] = "U"


    return "".join(s)

print to_rna("ACGTGGTCTTAA")
