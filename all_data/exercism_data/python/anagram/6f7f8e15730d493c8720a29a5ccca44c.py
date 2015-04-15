class Anagram:
    def __init__(self, base):
        self.comparison_base = self.sorted_string(base)

    def sorted_string(self, string):
        return sorted(string.lower())

    def match(self, strings):
        return [string for string in strings if self.sorted_string(string) == self.comparison_base]
