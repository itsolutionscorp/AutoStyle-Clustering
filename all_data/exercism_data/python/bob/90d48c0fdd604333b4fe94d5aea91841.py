class Bob():
    def hey(self, msg):
        if msg.isupper():
            answer = 'Woah, chill out!'
        elif msg.endswith('?'):
            answer = 'Sure.'
        elif msg.strip() is '':
            answer = 'Fine. Be that way!'
        else:
            answer = 'Whatever.'
        return answer
