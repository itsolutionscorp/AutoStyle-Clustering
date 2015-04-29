class Bob:
    def hey(self, phrase):
        typeofphrase = evaluatePhrase(phrase);
        if typeofphrase.checkValidChars() and typeofphrase.checkShout(): 
            return 'Whoa, chill out!';
        elif typeofphrase.checkQuestion():
            return 'Sure.';
        elif typeofphrase.checkSilence():
            return 'Fine. Be that way!';
        else: 
            return 'Whatever.';

class evaluatePhrase:
    def __init__(self, phrase):
        self.phrase = phrase;

    def checkShout(self):
        return self.phrase == self.phrase.upper();

    def checkQuestion(self):
        return self.phrase.endswith('?');

    def checkSilence(self):
        for x in self.phrase:
            if x != ' ' and x != '' and ord(x) != 9:
                return False;
        return True;

    def checkValidChars(self):
        for x in self.phrase:
            if (ord(x) >= 65 and ord(x) <= 90) or (ord(x) >= 97 and ord(x) <= 122):
                return True;
        return False;
