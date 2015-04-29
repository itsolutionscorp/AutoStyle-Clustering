def word_count(sentence):
  final_list = {}
  for word in sentence.split():
    if word in final_list:
      final_list[word] += 1
    else:
      final_list[word] = 1
  return final_list
