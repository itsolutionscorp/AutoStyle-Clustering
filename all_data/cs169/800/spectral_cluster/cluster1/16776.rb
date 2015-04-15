def combine_anagrams(words)

 amargan = Hash.new()

 words.each { |word|
  clave = word.downcase.chars.to_a.sort.to_s
  !amargan[clave] ? amargan[clave]=[word] : amargan[clave].push(word)
 }

 
 amargan.values

end
