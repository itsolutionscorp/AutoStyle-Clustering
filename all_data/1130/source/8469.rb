#=============================#
#       Part 3: anagrams      #
#=============================#

def combine_anagrams(words)
  words_array=Hash.new
  words.each do|word|
    normalized_word=word.downcase.split(//).sort.join
    puts normalized_word
    if words_array.member?(normalized_word)
      words_array[normalized_word]<< word
    else
      words_array[normalized_word] = [word]
    end
  end
  return words_array.values
end

