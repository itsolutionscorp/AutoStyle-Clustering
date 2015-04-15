import re

def word_count(text):
    text = text.lower().strip()
    words = text.split(' ')
    result = {}
    pattern = re.compile('[\W_]+')
    for word in words:
        cleaned_word = pattern.sub('', word)
        if cleaned_word == '':
            continue
        if (cleaned_word in result):
            result[cleaned_word] += 1
        else:
            result[cleaned_word] = 1
    return result
