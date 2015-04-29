def combine_anagrams(words)
  lst = words.each_index.group_by { |index| words[index].downcase.each_char.sort }.values
  return lst.map { |indices| indices.map { |index| words[index] } }
end

