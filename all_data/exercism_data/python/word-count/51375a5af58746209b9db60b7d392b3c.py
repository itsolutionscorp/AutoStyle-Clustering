from collections import Counter

def word_count(phrase):

    cleaned_phrase = ""

    # Remove anything not alphanumeric or a space
    for c in phrase:
        
        if (c.isalnum() or c == " "):
            cleaned_phrase += c.lower()

    return Counter(cleaned_phrase.split())
