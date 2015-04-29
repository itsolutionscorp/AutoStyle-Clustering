def combine_anagrams(words)
  index_hash = Hash.new
  anagram_array = Array.new
  words.map {|word| word.downcase.chars.sort.join}.uniq.each_with_index do |key, index| 
    index_hash[key] = index
    anagram_array[index] = Array.new
  end
  words.each {|word| anagram_array[index_hash[word.downcase.chars.sort.join]] << word}
  
  anagram_array
end