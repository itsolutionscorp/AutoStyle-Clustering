class Bob:
    """A teenager."""
    
    def hey(self, sentence):
        """Communicate with Bob."""
        sentence = sentence.strip()
        if not sentence:
            return "Fine. Be that way!"
        if sentence.isupper():
            return "Woah, chill out!"
        elif sentence.endswith('?'):
            return "Sure."
        elif sentence:
            return "Whatever."
