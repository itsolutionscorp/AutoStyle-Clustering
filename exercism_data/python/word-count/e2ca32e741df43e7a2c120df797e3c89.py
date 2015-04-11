import re

def word_count(input_string):
  word_pattern = re.compile('[\w\d]+\'*[\w\d]*', re.UNICODE)
  count_dict = {}
  input_string = input_string.lower()
  for word in re.findall(word_pattern, input_string):
    if word in count_dict:
      count_dict[word] += 1
    else:
      count_dict[word] = 1
  return count_dict
