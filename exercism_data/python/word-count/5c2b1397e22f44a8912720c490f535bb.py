def word_count(st):
  dic = {}
  words = st.split()
  
  for word in words:
    dic[word] = dic[word] + 1 if word in dic else 1

  return dic
