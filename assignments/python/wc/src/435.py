def word_count(string):
    word_list = string.split()
    unique = list(set(word_list))
    count = {i: word_list.count(i) for i in unique}
    return count
if __name__ == '__main__':
    print(word_count('na na\nla'))
