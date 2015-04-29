def detect_anagrams(str1, str_list):
    sortedStr = sorted(str1.lower())
    result = []
    for s in str_list:
        loweredCaseStr = s.lower()
        if len(sortedStr) == len(s) and str1 != loweredCaseStr and sortedStr == sorted(loweredCaseStr):
            result.append(s)
    return result
