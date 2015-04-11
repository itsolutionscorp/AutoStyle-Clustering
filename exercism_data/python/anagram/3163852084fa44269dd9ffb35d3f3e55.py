# vim:fileencoding=utf-8


def detect_anagrams(word, possible_anagrams):
    lowword = word.lower()
    wordlist = sorted(list(lowword))

    def _is_anagram(candidate_word):
        lowcandidate = candidate_word.lower()
        return lowcandidate != lowword \
               and sorted(list(lowcandidate)) == wordlist

    return list(filter(_is_anagram, possible_anagrams))
