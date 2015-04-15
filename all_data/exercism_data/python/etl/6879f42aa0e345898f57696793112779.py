def transform(data):
    transformed = {}
    for value, words in data.items():
        words = [w.lower() for w in words]
        transformed.update(dict.fromkeys(words, value))
    return(transformed)
