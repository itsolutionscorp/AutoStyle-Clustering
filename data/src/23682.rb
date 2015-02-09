def combine_anagrams(words)
  hash = Hash.new([])
  words.each { |str| hash[str.downcase.split(//).sort.join] += [str]}
  hash.values
  hash.values.to_s
end

words = ['Cars', ' ', 'C', 'for', 'potatoes', 'racs', 'four','SCAR', 'creams', 'scream']
empty_array = []
single_character = ['C', 'B', 'D']
puts "Example from Homework PDF"
puts combine_anagrams(words)
puts "Example empty array"
puts combine_anagrams(empty_array)
puts "Example single-character with capital letters"
puts combine_anagrams(single_character)


