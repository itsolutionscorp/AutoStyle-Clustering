def combine_anagrams(words)
  result_array = []
  words.each do |word|
    if (result_array.find { |word_array| word_array[0].downcase.chars.sort == word.downcase.chars.sort })
      result_array.find { |word_array| word_array[0].downcase.chars.sort == word.downcase.chars.sort }.
      push word
    else
      result_array.push [word]
    end
  end
  return result_array
end
