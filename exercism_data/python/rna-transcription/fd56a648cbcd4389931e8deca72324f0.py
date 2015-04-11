def to_rna(DNA):
    translate = {"C":"G", "G":"C", "A":"U", "T":"A"}
    RNA = ""
    for char in DNA:
        RNA += translate[char]
    return RNA
