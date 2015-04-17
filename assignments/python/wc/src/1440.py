import sys
import string
def word_count(phrase):
    words = dict()
    phrase = phrase.translate(None, string.punctuation)
    for word in phrase.split(" "):
        word = word.lower()
        if word == "":
            continue
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words
if __name__ == "__main__":
    if sys.argv[1]:
        word_count(sys.argv[1])
    else:
        print "Give phrase as argument"
