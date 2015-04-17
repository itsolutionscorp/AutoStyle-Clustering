__author__ = 'Arno'
def word_count(line):
    start_char = 0
    end_char = 0
    words = {}
    for i in range(len(line)):
        if line[i].isalnum() and (i == 0 or not line[i-1].isalnum()):
            start_char = i
            for j in range(i, len(line)):
                if line[j].isalnum() and(j == len(line)-1 or not line[j+1].isalnum()):
                    end_char = j
                    word = line[i:j+1]
                    if word in words:
                        words[word] += 1
                    else:
                        words[word] = 1
                    i = j
                    break
    return words
