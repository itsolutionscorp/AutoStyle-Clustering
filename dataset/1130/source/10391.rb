# Assignment submitted by
# Noel E. del Castillo
# date: 6/3/2012

#Section A
def combine_anagrams(words)
  results = []
  hash_result = words.each_with_object(Hash.new []) do |word, hash|
    #result << group_word(word, result)
    hash[word.downcase.chars.sort] += [word]
  end
  hash_result.each_value do |value|
    results << value
  end
  results
end

#Result A
puts "#{combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])}"
