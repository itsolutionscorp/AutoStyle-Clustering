#wordcount

def word_count(text):
    parsed = text.split()
    words = set(parsed)
    output = {x: parsed.count(x) for x in words}
    return output
