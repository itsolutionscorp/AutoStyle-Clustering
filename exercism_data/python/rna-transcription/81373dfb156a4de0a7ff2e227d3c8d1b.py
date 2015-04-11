def to_rna(strand):
    tr = {
        "G":"C",
        "C":"G",
        "A":"U",
        "T":"A",
        }
    arr = list(strand)
    for i in range(0,len(strand)):
        arr[i]=tr[arr[i]]
    return "".join(arr)
