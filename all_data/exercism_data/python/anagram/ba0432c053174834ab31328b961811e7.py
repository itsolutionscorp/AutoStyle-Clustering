class Anagram:
    def __init__(self, base):
        self.comparison_base = self.sorted_string(base)

    def sorted_string(self, string):
        return sorted(string.lower())

    def is_anagram(self, string):
        return self.sorted_string(string) == self.comparison_base

    def match(self, strings):
        return [string for string in strings if self.is_anagram(string)]
