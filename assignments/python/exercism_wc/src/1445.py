from string import punctuation
def word_count(some_string):
    some_string = some_string.lower()
    for character in punctuation:
        some_string = some_string.replace(character, '')
    word_list = some_string.split()
    some_string  += ' '
    answers = {word : some_string.count(str(word + ' ')) for word in word_list}
    return answers
