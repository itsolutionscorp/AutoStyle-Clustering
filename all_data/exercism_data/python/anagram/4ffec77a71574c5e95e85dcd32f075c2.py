def detect_anagrams(word, word_list):
    #  Create a list comprehenion
    sublist = [x for x in word_list  # Take all elements of the list
               # If the strings have the same letters (when lowered and sorted)
               if ''.join(sorted(x.lower())) == ''.join(sorted(word.lower()))
               # And not if the strings are identical i.e. only anagrams
               and x.lower() != word.lower()]
    return sublist
