class Word:
    """
    Class to manage tool for manage Words
    """

    @staticmethod
    def word_count(phrase):
        """
        Given a phrase return the occurrences of each word in it
        """
        words = {}
        phrase = Word.clean(phrase)
        phrase = phrase.split()
        for word in phrase:
            try:
                word = word.strip()
                words[word] += 1
            except KeyError:
                words[word] = 1
        return words

    @staticmethod
    def clean(phrase):
        """
        Clean phrase of returns and spaces
        """
        return phrase.rstrip().strip()


def word_count(phrase):
    return Word.word_count(phrase)
