def word_count(words):
    word_list=words.split()
    word_set= set(word_list)
    L=len(word_list)
    count={}
    for x in word_set:
        i=0
        for n in range(0,L):
            if x==word_list[n]:
                i=i+1
        count.update({x:i})
    return (count)
