def word_count(txt):
    for i in txt:
        if not (i == " " or i.isdigit() or i.isalpha()):
            txt = txt.replace(i,"") 
    txt2 = txt.lower()
    list = sorted(txt2.split()) 
    words = sorted(set(list))
    count = []
    for i in words:
       count.append(0)
       for j in list:
          if i == j:
              count[words.index(i)] += 1
    word_and_count = zip(words,count)
    word_count_dict = {}
    for words, count in word_and_count:
        word_count_dict[words] = count
    return word_count_dict
