""" Detect anagrams within a list for inputs:
    s: String to search for anagrams
    l: List of strings
"""
def detect_anagrams(s,l):
    # Created a sorted string in lowercase for comparison
    _sorted = ''.join(sorted(s.lower()))
    anagrams = []
    for w in l:
        # Create lowercase temp strings (case insensitive comparison)
        temp_w = w.lower()
        temp_s = s.lower()
        # Strings are same length but not identical
        if len(w) == len(s) and temp_w != temp_s:
            # Strings contains same characters, append to anagrams list
            if ''.join(sorted(temp_w)) == _sorted:
                anagrams.append(w)

    return anagrams
