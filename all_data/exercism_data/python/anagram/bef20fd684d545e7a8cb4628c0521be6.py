from copy import copy

class Anagram():
    anagram = ""

    def __init__ (self, anagram):
        self.anagram = anagram

    def match(self, candidates):
        # Remove same words candidates
        candidates = [candidate for candidate in candidates 
            if candidate != self.anagram]
        #Remove wrong length candidates
        candidates = [candidate for candidate in candidates 
            if len(candidate) == len(self.anagram)]

        backup = copy(candidates)
        # Ignore case
        anagram = self.anagram.lower()
        candidates = [candidate.lower() for candidate in candidates]
        for char in anagram:
            for i, candidate in enumerate(candidates):
                if char in candidate:
                    # Remove matched characters
                    candidates[i] = candidates[i].replace(char, "", 1)
        # Add matched candidates to result
        result = []
        for i in zip(candidates, backup):
            if i[0] == "" and i[1] not in result:
                result.append(i[1])
        return result
