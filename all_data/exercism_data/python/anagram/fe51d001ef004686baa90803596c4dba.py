def get_letter_count(word):
    letter_counts = {}
    for letter in word:
        letter_counts[letter] = word.count(letter)
    return letter_counts


def detect_anagrams(word, anagram_list):

    output_list = []

    for anagram in anagram_list:
         if get_letter_count(word.lower()) == get_letter_count(anagram.lower()) and anagram.lower() != word.lower():
            output_list.append(anagram)

    return output_list
