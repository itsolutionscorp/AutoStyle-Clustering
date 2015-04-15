class Bob:
    def hey(self, phrase):
        if self.is_shouting(phrase):
            return "Woah, chill out!"
        if self.is_question(phrase):
            return 'Sure.'
        if self.is_silence(phrase):
            return 'Fine. Be that way!'
        return 'Whatever.'

    def is_shouting(self, phrase):
        return phrase.isupper()

    def is_silence(self, phrase):
        return not bool(phrase.strip())

    def is_question(self, phrase):
        return (not self.is_silence(phrase)) and phrase.endswith('?')
