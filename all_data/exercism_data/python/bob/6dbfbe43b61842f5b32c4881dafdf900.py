class Bob:
    YELLING = 0
    QUESTION = 1
    SILENCE = 2
    STATEMENT = 3
    response = {
        YELLING: 'Woah, chill out!',
        QUESTION: 'Sure.',
        SILENCE: 'Fine, be that way.',
        STATEMENT: 'Whatever.'
    }

    def hey(self, speech):
        speech_type = self.STATEMENT
        if speech.strip() == '':
            speech_type = self.SILENCE
        elif speech.upper() == speech:
            speech_type = self.YELLING
        elif speech[-1] == '?':
            speech_type = self.QUESTION

        return self.response[speech_type]
