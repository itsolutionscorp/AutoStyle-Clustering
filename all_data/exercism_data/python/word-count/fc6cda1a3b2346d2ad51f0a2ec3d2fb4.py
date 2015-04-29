def word_count(string):
 list=string.split()
 return {x:list.count(x) for x in list}
