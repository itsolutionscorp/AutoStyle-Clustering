import unicodedata


class Bob(object):
    speech_to_response = {
        'silence': 'Fine. Be that way!',
        'question': 'Sure.',
        'yell': 'Woah, chill out!',
    }

    def hey(self, speech):
        classification = self._classify(speech)
        return self.speech_to_response.get(classification, 'Whatever.')

    def _classify(self, speech):
        if not speech or len(speech.strip()) == 0:
            return 'silence'
        speech = unicode(speech, 'latin-1')
        speech = unicodedata.normalize('NFKD', speech)
        if speech.isupper():
            return 'yell'
        if speech.endswith('?'):
            return 'question'
