def combine_anagrams(words)
  [] if words.empty?
  words.group_by { |element| element.downcase.chars.sort }.values
end

