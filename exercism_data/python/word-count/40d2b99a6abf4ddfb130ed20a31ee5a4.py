class Phrase:

    def __init__(self, phrase):
        self.phrase = phrase

    def word_count(self):
        raw = self.phrase.translate(''.join(chr(c).lower() if chr(c).isalnum() else ' ' for c in range(256)))
        words = raw.split()
        red = {}

        for word in words:
            try:
                red[word] += 1
            except:
                red[word] = 1
        return red
