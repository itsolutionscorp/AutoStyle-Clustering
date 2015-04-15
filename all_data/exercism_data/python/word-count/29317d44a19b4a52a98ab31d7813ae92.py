class Phrase:

    def __init__(self,phrase):
        self.phrase = phrase

    def word_count(self):
        words = strjoin(char.lower() for char in self.phrase 
            if self._char_is_valid(char)).split()
        count = {}
        while len(words)>0:
            char = words.pop()
            if not char in count.keys():
                count[char] = 0
            count[char] += 1
        return count

    def _char_is_valid(self,char):
        return (char.isalnum() or char.isspace())


def strjoin(list_of_strings):
    return "".join(list_of_strings)
