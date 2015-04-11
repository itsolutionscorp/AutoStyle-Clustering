from collections import defaultdict

def word_count(st):
  dic = defaultdict(int)
  words = st.split()
  
  for word in words:
    dic[word] += 1

  return dic
