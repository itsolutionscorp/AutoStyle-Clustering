__author__ = 'romleinster'
def word_count(phrase):
    output = {}
    words = phrase.split()
    for word in words:
        count = 0
        for check in words:
            if word == check:
                count += 1
        output.update({word: count})
    return output
