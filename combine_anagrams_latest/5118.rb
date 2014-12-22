def combine_anagrams words
  words.group_by { |element| element.downcase.chars.sort }.values
end  