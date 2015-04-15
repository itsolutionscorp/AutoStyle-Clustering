# First, we take the DNA string and break it out into a list called
# dna[]. We then initialize an empty list for our corresponding
# RNA, called rna[]. We create a dictionary called  replacementDict
# that contains mappings of the translations from DNA to RNA. We
# Then loop through the replacementDict and compare each key to the
# elements in the dna[] list. Replace them according to their value
# and append that value to the rna[] list. Then, join the rna[] list
# into a single string and return that string.


def to_rna(DNAstring):
    """ Takes in a DNA string as input and returns the corresponding
        RNA string.
    """

    dna = list(DNAstring)
    rna = []

    replacementDict = {"G": "C", "C": "G", "T": "A", "A": "U"}

    for key, value in replacementDict:
        if key == item in dna:
            rna.append(key)

    return "".join(rna)
