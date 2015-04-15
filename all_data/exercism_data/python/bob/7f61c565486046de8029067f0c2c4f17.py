from re import search


class Bob():
    """Speech in the eyes of Mighty Bob"""
    def hey(s, speech):
        """hey(speech) will return response"""
        if speech:
            speech = speech.decode('unicode-escape')
        if not speech or search(r"^\s*$", speech):
            return 'Fine. Be that way!'
        if speech.isupper():
            return 'Woah, chill out!'
        if search(r"\w\?\s*$", speech):
            return "Sure."
        return 'Whatever.'
