def detect_anagrams(word,w_list):
    sub_w_list = []
    for an in w_list:
        if sorted(list(word.lower())) == sorted(list(an.lower())) and word.lower() != an.lower():
            sub_w_list.append(an)
    return sub_w_list
