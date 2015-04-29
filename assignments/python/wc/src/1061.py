def word_count(phrase):
    word_tally = {}
    current_word = ''
    space = ' '
    if '\\n' in phrase:
        phrase = phrase.replace('\\n', ' ')
        print('No newline phrase:', phrase)
    for index in range(len(phrase)):
        if phrase[index] == space:
            if len(current_word) > 0:
                if current_word in word_tally:
                    word_tally[current_word] += 1
                    current_word = ''
                else:
                    word_tally[current_word] = 1
                    current_word = ''
        elif (index == len(phrase) -1):
            if len(current_word) > 0:
                current_word = current_word + phrase[index]                
                if current_word in word_tally:
                    word_tally[current_word] += 1
                else:
                    word_tally[current_word] = 1
        else:
            current_word = current_word + phrase[index]
    print(word_tally)
    return word_tally
phrase = input('Enter the phrase to be counted: ')
print('Phrase as input:', phrase)
results = word_count(phrase)
print(results)
