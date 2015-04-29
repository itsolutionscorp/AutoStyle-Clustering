def word_count(words):
    import string
    exclude = set(string.punctuation)
    words = words.lower()
    words = words.split()
    answer = {}
    for word in words:
        word = ''.join(ch for ch in word if ch not in exclude)
        if len(word) > 0:
            count = 0
            for otherword in words:
                otherword = ''.join(ch for ch in otherword if ch not in exclude)
                if word == otherword:
                    count = count + 1
            answer[word] = count
    return answer
