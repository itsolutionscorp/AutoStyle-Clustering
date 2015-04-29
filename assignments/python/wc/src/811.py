from collections import Counter
def word_count(text):
    """Counts the occurences of each word, returning a dict."""
    return dict(Counter(text.split()))
