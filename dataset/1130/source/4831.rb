def combine_anagrams(list)
  list.group_by { |element| element.downcase.chars.sort }.values
end
