def distance(s1, s2):

    if len(s1) != len(s2):
        return "Strands are different lengths"

    distance = 0
    for i,l in enumerate(s1):
        if s2[i] != l:
            distance += 1

    return distance

if __name__ == "__main__":

    s1 = "AAGTCA"
    s2 = "AAGTCG"
    s3 = "AGGTCA"
    s4 = "AAAAAA"
    s5 = "AA"

    print distance(s1, s2)
    print distance(s1, s3)
    print distance(s1, s4)
    print distance(s1, s5)
    
