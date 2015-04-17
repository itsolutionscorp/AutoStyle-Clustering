def word_count(txt):
    for char in txt:
        if not (char == " " or char.isdigit() or char.isalpha()):
            txt = txt.replace(char,"") 
    lwr = txt.lower()
    list = sorted(lwr.split()) 
    words = sorted(set(list))
    count = []
    for word1 in words:
       count.append(0)
       for word2 in list:
          if word1 == word2:
              count[words.index(word1)] += 1
    word_and_count = zip(words,count)
    word_count_dict = {}
    for words, count in word_and_count:
        word_count_dict[words] = count
    return word_count_dict
