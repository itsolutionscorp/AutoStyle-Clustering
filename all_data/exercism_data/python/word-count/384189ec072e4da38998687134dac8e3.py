def word_count(phrase):
    list=[]
    list = phrase.split(" ")
    answer={}

    for word in list:
        if word.find("\n") >=0:
            originalword = word
            word = word.split("\n")
            i = 0
            for i in range(len(word)):
                list.append(word[i])
            del list[list.index(originalword)]

    for word in list:
        if word not in answer.keys() and word!= "":
            count = list.count(word)
            answer[str(word)] = count

    return answer
