def combine_anagrams(words)
 anagrams = words.group_by { |element| element.downcase.chars.sort }.values
 return anagrams
end