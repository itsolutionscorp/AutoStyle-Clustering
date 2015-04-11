def hey(what):
    return Bob().listen_and_reply(what)

class Silence:
    def listen(self, what): return what.strip() == ''
    def reply(self): return 'Fine. Be that way!'

class Yell:
    def listen(self, what): return what.upper() == what and what.lower() != what
    def reply(self): return 'Whoa, chill out!'

class Question:
    def listen(self, what): return what.strip()[-1] == '?'
    def reply(self): return 'Sure.'

class Other:
    def listen(self, what): return True
    def reply(self): return 'Whatever.'

class Bob:
    def listen_and_reply(self, what):
        for x in [Silence(), Yell(), Question(), Other()]:
            if x.listen(what): return x.reply()
