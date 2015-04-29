from re import search


class Bob():
    """Speech in the eyes of Mighty Bob"""
    def hey(s, speech):
        """hey(speech) will return response"""
        # Speech may be in unicode - will interfere with Yelling
        speech = speech.decode('unicode-escape')
        empty_speech = search(r"^\s*$", speech)
        question = search(r"\w\?\s*$", speech)
        yelling = speech.isupper()
        if not speech or empty_speech:
            return 'Fine. Be that way!'
        if yelling:
            return 'Woah, chill out!'
        if question:
            return "Sure."
        return 'Whatever.'
