class Phrase:
    """Represents a phrase for the purpose of computing a word count."""

    # Punctuation that separates words
    PUNCTUATION = list("""!@#$%^&*()_+=[]{};'":?></.,~`|\\""")

    def __init__(self, phrase):
        # Strings are immutable, so we need to work on a list while replacing characters.
        phrase = list(phrase)
        
        # Replace all punctuation with spaces.
        for (i, character) in enumerate(phrase):
            if character in Phrase.PUNCTUATION:
                phrase[i] = ' '

        # Turn it back into a string
        phrase = ''.join(phrase)

        # Normalise case (PYTHON, Python, python and PyThOn are all words)
        phrase = phrase.lower()

        # Maps: word -> word count for that word
        self.words = {}

        # Compute the word count.
        for word in phrase.split():
            if word in self.words:
                self.words[word] += 1
            else:
                self.words[word] = 1

    def word_count(self):
        return self.words
