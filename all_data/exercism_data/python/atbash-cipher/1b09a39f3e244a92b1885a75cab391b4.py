#Create an implementation of the atbash cipher

import re
alphabetD={}
cypherD={}

#look for:!!!!!!!!!!!!!
#why when i put the last char ont he list a and z it duplicates teh
# list at the past part of the while iteration so that cypher is copied from alphabet???



#populate teh list
alphabet="abcdefghijklmnopqrstuvwxy"
cypher="zyxwvutsrqponmlkjihgfedcb"
c=0    
alphabetD['a']='z'
cypherD['z']='a'
while(c < len(alphabet)):
    alphabetD[alphabet[c]]=cypher[c]
    cypherD[cypher[c]]=alphabet[c]
    c+=1
    
    
def decode(sentence):
    sentence=normalize(sentence)
    decodedText=""
    for c in sentence:
        decodedText+= cypherD[c]
        
    return decodedText

def encode(sentence):
    sentence=normalize(sentence)
    encodedText=""
    i=1  
    for c in sentence:
        encodedText+= alphabetD[c]
        if(i%5 == 0):
            encodedText+= " "
        i+=1
    
    return encodedText

def normalize(sentence):
    sentence=re.sub(' ','',sentence)
    sentence=re.sub('[.,]','',sentence)
    sentence=sentence.lower()
    
    return sentence
