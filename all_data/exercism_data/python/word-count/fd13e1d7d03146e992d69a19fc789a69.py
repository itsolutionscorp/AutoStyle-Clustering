def word_count(string):
    words = string.split()
    counts = {}
    for current_word in words:
        if not counts.has_key(current_word):
             counts[current_word] = sum([entry == current_word for entry in words])
    return counts
