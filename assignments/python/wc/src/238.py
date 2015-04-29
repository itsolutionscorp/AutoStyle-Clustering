__author__ = 'nebur1989'
def word_count(phrase):
    dic = {}
    words = []
    current_word = ""
    for character in phrase:
        character = character.lower()
        if character.isalnum():
            current_word += character
        elif len(current_word) > 0:
            words.append(current_word)
            current_word = ""
    if len(current_word) > 0:
        words.append(current_word)
    for word in words:
        if word in dic:
            dic[word] += 1
        else:
            dic[word] = 1
    return dic
