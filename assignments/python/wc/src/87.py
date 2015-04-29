def word_count(phrase):
   list_word = {}
   lista = phrase.split()
   for i in lista:
      list_word[i] = lista.count(i)
   return list_word
