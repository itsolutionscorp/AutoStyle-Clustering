def word_count(phrase):

    dict = {}
    cleaned_phrase = ""

    # Remove anything not alphanumeric or a space
    for c in phrase:
        
        if (c.isalnum() or c == " "):
            cleaned_phrase += c

    for w in cleaned_phrase.split():
        word = w.lower()

        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1

    return dict
        
