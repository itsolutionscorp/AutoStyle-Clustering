from collections import Counter


class Anagram(object):
    def __init__(self, target):
        self.target = target

    def match(self, candidates):
        target = self.target.lower()
        target_histo = Counter(target)

        result = []
        for s in candidates:
            sl = s.lower()
            if target_histo == Counter(sl) and sl != target:
                result.append(s)
        return result
