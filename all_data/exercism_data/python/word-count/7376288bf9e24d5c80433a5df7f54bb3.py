def word_count(string):
        counts={}
	words=string.split()
        for word in words:
        	word=word.strip()
                if counts.get(word)==None:
                     counts[word]=1
                else:
                     counts[word]+=1
        return counts       
