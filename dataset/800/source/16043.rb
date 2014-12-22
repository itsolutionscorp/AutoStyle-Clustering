def combine_anagrams(words)
  hash = {}
  returnArray = []
  words.each do |word| 
    hash[word.downcase.chars.sort] ||= []
    hash[word.downcase.chars.sort] << word
  end
  hash.each_value { |value| returnArray << value }
  return returnArray
end