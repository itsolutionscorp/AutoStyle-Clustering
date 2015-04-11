import string
def detect_anagrams(target,list_of_words):
    def letter_freq(word):
        dic={letter:0 for letter in string.lowercase}
        for letter in word:
            dic[letter.lower()]+=1
        return [dic[letter] for letter in string.lowercase]
    target_letter_freq=letter_freq(target)
    return [word for word in list_of_words if letter_freq(word)==target_letter_freq and word.lower()!=target.lower()]
