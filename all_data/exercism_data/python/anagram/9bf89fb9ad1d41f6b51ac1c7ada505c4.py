from collections import Counter

class Anagram(object):
    def __init__(self, target):
        self.target = target.lower()

    def match(self, possible_anagrams):
        """Find anagrams of target contained within possible_anagrams list."""
        answers = []
        for word in possible_anagrams:
            if (word.lower() != self.target and
                Counter(word.lower()) == Counter(self.target)):
                answers.append(word)
        return answers
