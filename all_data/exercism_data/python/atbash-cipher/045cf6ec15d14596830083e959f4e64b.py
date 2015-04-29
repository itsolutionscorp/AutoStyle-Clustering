def encode(string):
    s = "".join(list(c for c in string.lower() if c.isalpha() or c.isdigit()))
    a = "".join(list(map(chr,list(range(ord("a"),ord("z")+1)))))
    s_e_prot = s.translate(s.maketrans(a,a[::-1]))
    if len(s_e_prot) % 5 == 0:
        s_e_list = [""] * (len(s) // 5)
    else:
        s_e_list = [""] * ((len(s) // 5)+1)
    i = 1
    j = 0
    for char in s_e_prot:
        s_e_list[j] += char
        if i % 5 == 0:
            j += 1
        i += 1
    s_e = " ".join(s_e_list)
    return s_e
    
def decode(string):
    s_e = "".join(list(c for c in string.lower() if c.isalpha() or c.isdigit()))
    a = "".join(list(map(chr,list(range(ord("a"),ord("z")+1)))))
    s_d = s_e.translate(s_e.maketrans(a,a[::-1]))
    return s_d
