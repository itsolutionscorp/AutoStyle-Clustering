def combine_anagrams(words)
  resultado = Hash.new
  words.each do |word|
    char_array = Array.new
    word.downcase.chars { |char| (char_array << char) }
    sort_char_array = char_array.sort
    resultado[sort_char_array] = if (resultado[sort_char_array] == nil) then
      [word]
    else
      (resultado[sort_char_array] << word)
    end
  end
  resultado.values
end

