def hey(greeting):

    def yell(g):
        g = g.replace("!", "")
        words = [w for w in g.split(' ') if w.isalpha()]
        return len(words) > 0 and all(w.isupper() for w in words)

    if greeting.strip() == "":
        return 'Fine. Be that way!'

    if yell(greeting):
        return 'Whoa, chill out!'

    if greeting.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
