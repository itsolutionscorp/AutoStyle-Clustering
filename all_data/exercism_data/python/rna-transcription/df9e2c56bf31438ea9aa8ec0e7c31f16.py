
def to_rna(strand):
    t = []
    d = {"G":"C", "C":"G", "T":"A", "A":"U"}
    for letter in strand:
        t.append(d.get(letter,letter))
    return "".join(t)




# print(to_rna("GVGCTATAU"))
