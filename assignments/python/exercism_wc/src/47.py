from collections import Counter
def word_count(my_sentence):
    return Counter(my_sentence.split())
if __name__ == '__main__':
    input = raw_input()
    print word_count(input)
