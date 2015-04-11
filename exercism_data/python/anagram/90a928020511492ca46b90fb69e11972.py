def remove(seq, index):
    return seq[:index].join(seq[index + 1:])

class Anagram:
    def __init__(self, word):
        self.word = word

    def match(self, candidates):
        if len(candidates) == 0:
            return []

        passing_terms = []
        word = self.word.lower()

        for candidate in candidates:
            x = candidate.lower()

            if len(x) == len(word) and x != word:
                for y in word.lower():
                    i = x.find(y)

                    if i >= 0:
                        x = remove(x, i)
                    else:
                        break

                if len(x) == 0:
                    passing_terms.append(candidate)

        return passing_terms
