def combine_anagrams(words)
#return words.group_by { |word| word.downcase.each_char.sort }.values
  lst = words.each_index.group_by { |index| words[index].downcase.each_char.sort }.values
  return lst.map { |indices| indices.map { |index| words[index] } }
end

input1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
output1 = combine_anagrams(input1)
puts output1.to_s
fail unless output1 == 
    [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]


