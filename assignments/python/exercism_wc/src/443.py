def word_count(text):
    count = {}
    text = text.strip()
    stuff = text.split()
    for x in stuff:
        if x == '':
            return text
        elif x in count:
           count[x] += 1
        else:
            count[x] = 1
    return count
