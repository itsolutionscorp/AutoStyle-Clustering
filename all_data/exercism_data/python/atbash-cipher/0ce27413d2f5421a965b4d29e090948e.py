def translate(word):
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    e = []
    for i in word.strip():
        try:
            i = i.lower()
            x = alphabet.index(i) + 1
            diff = 26-x
            e.append(alphabet[diff])
        except ValueError:
            try:
                int(i)
                e.append(i)
            except:
                pass
    return ''.join(e)

def decode(word):
    translated = translate(word)
    return translated

def encode(word):
    translated = translate(word)
    return ' '.join(translated[i:i+5] for i in xrange(0, len(translated), 5))
