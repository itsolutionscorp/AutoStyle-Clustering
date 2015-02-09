def combine_anagrams(words)
  anagramas = Hash.new
  final_array = Array.new
  words.each do |word|
    word1 = word.split(//).sort.join;                             #divide as palavras em uma array de letraas coloca em ordem e as junta novamente como string
    word1.downcase!                                               #Trabalha com strings em downcase

    if anagramas[word1] == nil                                    #se nao existe a key sort do anagrama ele cria a key e seta uma nova array
      anagramas[word1] = Array.new
    end
    anagramas[word1]<< word                                       #Joga a palavra crua na array

  end
  anagramas.each do |key, value|                                  #pega o valor de cada key e joga dentro de uma array final
  #p "key: #{key}"
  #p "value #{value}"
    final_array<<value
  end
  return final_array
end

a = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

combine_anagrams(a)
