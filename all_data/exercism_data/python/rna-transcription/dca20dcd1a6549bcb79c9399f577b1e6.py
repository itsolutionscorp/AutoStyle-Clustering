def to_rna(word):
    translation ={"G" : "C", "C": "G", "T": "A", "A": "U"}
    newWord = ""
    for char in word:
        newWord += translation[char]

    return newWord
