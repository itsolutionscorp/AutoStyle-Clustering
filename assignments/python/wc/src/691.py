import string
def word_count(self):
    self = self.lower().translate(self.maketrans('','',string.punctuation))
    words = self.split()
    word_dict = {}
    for word in words:
        if word not in word_dict:
            word_dict[word] = 1
        else:
            word_dict[word] += 1
    return word_dict
