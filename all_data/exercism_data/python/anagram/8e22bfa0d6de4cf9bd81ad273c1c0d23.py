def detect_anagrams(target,candidates):
    """given a target sequence and a list of candidates strings,
    returns a list of candidates that are anagrams of 'target'"""

    candidates = [c for c in candidates if len(c) == len(target)]

    def isanagram(orig,other):
        """returns True if strings are anagrams, False otherwise"""
        assert type(orig) == type(other) == str  # better safe than sorry :)
        orig, other = orig.lower(), other.lower()  # case-insensitive
        if orig == other:
            return False  # same word is not an anagram
        other = list(other)  # one char per item
        for c in orig:
            if c in other:
                other.remove(c)  # eliminate this item
            else:
                return False
        # end of loop, anything left ?
        if other == []:
            return True
        return False  # fallack to False in case something went strange

    return [c for c in candidates if isanagram(c,target)]
