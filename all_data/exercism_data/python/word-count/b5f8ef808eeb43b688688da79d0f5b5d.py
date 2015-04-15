class Phrase(object):
    def __init__(self, text_phrase):
        self.text_phrase = text_phrase

    def word_count(self):
        if not self.text_phrase: return {}

        count_obj = {}

        for word in self.text_phrase.split(' '):
            word = ''.join([s for s in word if s.isalnum()]).lower()
            if word:
                if count_obj.get(word):
                    count_obj[word] += 1
                else:
                    count_obj[word] = 1

        return count_obj
