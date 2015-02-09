def combine_anagrams(words)
  ordenados = Array.new
  anagramas = Array.new
  words.each do |word|
    ordenados << word.downcase.chars.sort(&:casecmp).join
  end
  ordenados.uniq.each do |ordenado|
    temporal = Array.new
    words.each do |word|
      if ((word.downcase.chars.sort(&:casecmp).join.casecmp ordenado) == 0)
          temporal << word
      end
    end
    anagramas[anagramas.size] = temporal
  end
  return anagramas
end
