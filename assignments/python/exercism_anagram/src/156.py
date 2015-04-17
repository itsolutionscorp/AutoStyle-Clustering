#
#
#
def detect_anagrams(word,candidates):
    anagrams = []
    # Lower and sort characters of test word
    sorted_word = (''.join(sorted(word.lower())))
    for cand in candidates:
        # Lower and sort characters of candidate
        sorted_candidate = (''.join(sorted(cand.lower())))
        if (sorted_word == sorted_candidate) and (word.lower() != cand.lower()):
            # Lower, sorted chars are the same but the original words are different
            anagrams.append(cand)
    return anagrams
