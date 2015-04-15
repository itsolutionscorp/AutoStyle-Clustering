from collections import Counter as cnt

def word_count(string):
    answer = cnt()
    for i in string.split():
        answer[i] += 1
    return answer
