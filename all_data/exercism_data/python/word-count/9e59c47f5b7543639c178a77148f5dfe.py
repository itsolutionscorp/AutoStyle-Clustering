from collections import Counter

def word_count(text):
    stripped_text = ''.join(c for c in text.lower()
                            if c.isalnum() or c.isspace())
    return Counter(stripped_text.split())
