def word_count(ans):
    a = ans.strip();
    count = {};
    read = [];
    a = a.replace('\n', ' ');
    wordList = a.split(' ');
    print wordList;
    for x in wordList:
        if x and x not in read:
        		#print x;
        		count[x] = wordList.count(x);
        		read.append(x);
    return count;
if __name__ == "__main__":
    ans = str(raw_input("Input a string: "));
    print word_count(ans);
