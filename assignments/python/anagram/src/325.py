def detect_anagrams(str,lst):
    sstrl = sorted(str.lower())
    ans = []
    for i in lst:
        if str.lower() == i.lower():
            pass
        elif sstrl == sorted(i.lower()):
            ans.append(i)
    return ans
