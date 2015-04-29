def word_count(str):
   words = []
   dict = {}
   str = str.replace("\n"," ")
   words = str.split(" ")
   for w in words:
      if w:
         if w in dict:
            dict[w] = dict[w] + 1
         else:
            dict[w] = 1
   return dict
