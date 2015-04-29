def detect_anagrams(entry, candidates):

    def flatten(tpl):
        ret = []
        for i in tpl:
            if isinstance(i, list) or isinstance(i, tuple):
                ret.extend(flatten(i))
            else:
                ret.append(i)
        return ret

    #flatten and convert tuples to lists
    anagramlist = flatten(candidates)
    anagram = []

    for element in anagramlist:

        #check for matching words and reject them
        if element.lower() != entry.lower():

            #check for anagrams by comparing two lowercase sorted list
            if sorted(entry.lower()) == sorted(element.lower()):
                anagram += [element]

    return anagram
