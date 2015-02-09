def combine_anagrams(words)
  aux = []
  words.each do |word|
    name = word.downcase.split("").sort.join("").delete(" ")
    aux.push(name) if (not aux.include?(name))
  end
  respuesta = []
  aux.each do |palabra|
    temp = []
    words.each do |word|
      if palabra.eql?(word.downcase.split("").sort.join("").delete(" ")) then
        temp.push(word)
      end
    end
    respuesta.push(temp)
  end
  return respuesta
end