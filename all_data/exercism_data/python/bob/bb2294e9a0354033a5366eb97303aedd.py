class Bob:
    def hey(bob, message):
        if len(message.strip()) == 0:
            return 'Fine. Be that way!'
        else:
            upper = sum(map(lambda x: x.isupper(), message))
            lower = sum(map(lambda x: x.islower(), message))
            if upper > 0 and lower == 0:
                return 'Woah, chill out!'
            elif message[-1] == "?":
                return "Sure."
            else:
                return 'Whatever.'
