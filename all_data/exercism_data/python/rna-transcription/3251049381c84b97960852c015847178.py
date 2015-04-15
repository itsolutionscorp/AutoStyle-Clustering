def to_rna(strand):
    key = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    answer = ""
    for x in strand:
        answer += key[x]  # for each DNA element add RNA element from
# key to answer
    return answer
