import string

class Bob:

    def question(phrase):
        return phrase[-1] is '?'

    def yell(phrase):
        return phrase.upper() == phrase and any(c in string.ascii_letters for c in phrase)

    def addressing(phrase):
        return phrase is None or len(phrase) is 0

    reactions = [
        (addressing, 'Fine. Be that way!'),
        (yell, 'Woah, chill out!'),
        (question, 'Sure.')
    ]

    defaultAnswer = 'Whatever.'

    def hey(self, phrase):
        phrase = phrase.strip()
        for fun, answer in self.reactions:
            if fun(phrase):
                return answer
        return self.defaultAnswer
