def to_rna(x):
    conv = ""
    conv_dic = {"G":"C", "C":"G", "T":"A", "A":"U"}
    for letter in x:
        conv += conv_dic[letter]
    return conv
