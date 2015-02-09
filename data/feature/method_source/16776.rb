def combine_anagrams(words)
  amargan = Hash.new
  words.each do |word|
    clave = word.downcase.chars.to_a.sort.to_s
    if (not amargan[clave]) then
      amargan[clave] = [word]
    else
      amargan[clave].push(word)
    end
  end
  amargan.values
end