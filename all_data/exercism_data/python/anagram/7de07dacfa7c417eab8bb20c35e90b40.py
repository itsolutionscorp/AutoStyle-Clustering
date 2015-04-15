class Anagram(object):
    def __init__(self, target):
        self.target = target
        self.sorted_target = sorted(target.lower())

    def match(self, potentials):
        results = []
        for potential in potentials:
            if self.target != potential and self.sorted_target == sorted(potential.lower()):
                results.append(potential)
        return results
