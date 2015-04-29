def word_count(string):
    strCount = {}
    string = string.replace('\n', ' ').split(' ')
    for i in string:
        if i:
            if not i in strCount:
                strCount[i] = 1
            else:
                strCount[i] += 1
    return strCount
if __name__ == '__main__':
    print(word_count('wolf   terry'))
