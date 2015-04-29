def word_count(text):
    if not isinstance(text, basestring):
        return False
    keys = text.split()
    values = [None] * len(keys)
    for i, w in enumerate(keys):
        values[i] = keys.count(w)
    return dict(zip(keys, values))
