__author__ = 'tracyrohlin'

def to_rna(s):
    s = list(s)
    nucleotide_map = {
            "G": "C",
            "C": "G",
            "T": "A",
            "A": "U"
    }
    for index, item in enumerate(s):


        s[index] = nucleotide_map[item]

        """
        if s[num] == "G":
            s[num] = "C"
        elif s[num] == "C":
            s[num] = "G"
        elif s[num] == "T":
            s[num] = "A"
        else:
            s[num] = "U"""


    return "".join(s)

print to_rna("ACGTGGTCTTAA")
