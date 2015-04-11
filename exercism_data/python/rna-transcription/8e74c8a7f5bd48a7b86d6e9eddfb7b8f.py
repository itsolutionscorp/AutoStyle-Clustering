# DNA   -> RNA
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

transcriptions = {"G":"C",
                  "C":"G",
                  "T":"A",
                  "A":"U"}

def to_rna(dnastrand):
    try:
        rnastrand = "".join([transcriptions[n] for n in dnastrand])
        return rnastrand
    except KeyError:
        raise ValueError("Invalid input! This does not look like a strand of DNA.")
