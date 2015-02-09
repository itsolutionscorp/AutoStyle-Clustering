def combine_anagrams(words)
  anagramHash = Hash.new(Array.new)
  words.each do |word|
    if anagramHash[word.chars.sort.join.downcase].empty? then
      anagramHash[word.chars.sort.join.downcase] = Array.new
    end
    (anagramHash[word.chars.sort.join.downcase] << word)
    puts(word.chars.sort.join.downcase)
  end
  output = Array.new
  anagramHash.each_value { |value| (output << value) }
  return output
end