def to_rna(dna):
    transcriptions = {"G": "C", "C": "G", "T": "A", "A": "U"}
    res = ""
    for char in dna:
         if transcriptions.get(char) is None:
             return None
         res += transcriptions[char]
    return res
