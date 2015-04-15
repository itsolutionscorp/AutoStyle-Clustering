from collections import Counter


### Initially I've used a dictionary and manually iteratied and incremented the keys on the dictionary
### the Counter from collections makes this code much more elegant ( just my opinion though :D )



def word_count(text):
  return Counter(clean_text(text));

### Removes all non-alphanumeric characters, converts characters to lowercase and
### splits the  string into individual words
def clean_text(text):
  return "".join(c for c in text if c.isalnum() or c.isspace()).lower().split()



