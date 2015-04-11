def detect_anagrams(word, wordlist):
    word = word.lower()
    def lower_sort(s):
        return ''.join(sorted(s.lower()))
    ls_word = lower_sort(word)
    def is_anagram(w):
        return word != w.lower() and ls_word == lower_sort(w)
    return filter(is_anagram, wordlist)
