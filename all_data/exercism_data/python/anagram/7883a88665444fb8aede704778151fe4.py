def detect_anagrams(str1, str_list):
    sortedStr = sorted(str1.lower())
    result = [s for s in str_list
              if len(sortedStr) == len(s) and str1 != s.lower() and sortedStr == sorted(s.lower())]
    return result
