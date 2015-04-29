class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()

    def match(self, lista):
        matches = []
        new_word = []
        for i in lista:
            new_word = list(self.word)
            vale = True
            for j in i.lower():
                if new_word.count(j) > 0:
                    new_word.pop(new_word.index(j))
                else:
                    vale = False
                    break
            if vale:
                if i != self.word and len(new_word)==0:
                    matches.append(i)
        return matches
