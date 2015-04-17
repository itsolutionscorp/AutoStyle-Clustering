def detect_anagrams(str_ref, str_list):
    str_sorted = "".join(sorted(str_ref.lower()))
    return [ s for s in str_list if s.lower() != str_ref.lower() and str_sorted == "".join(sorted(s.lower())) ]
