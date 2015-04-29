class Anagram:

    def __init__(self, phrase):
        self.phrase = phrase

    def match(self, words):
        anagrams = []
        for word in words:
            if word != self.phrase:
              if sorted(word.lower()) == sorted(self.phrase.lower()):
                  anagrams.append(word)

        return anagrams
