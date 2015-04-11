def detect_anagrams(w, w_list):
    return filter(lambda x: "".join(sorted(list(x.lower()))) == "".join(sorted(list(w.lower()))), w_list)
