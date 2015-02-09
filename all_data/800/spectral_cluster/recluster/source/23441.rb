def are_anagram?(word1, word2)
  word1.downcase.each_char.map.sort == word2.downcase.each_char.map.sort
end

def combine_anagrams(words)
  result = Array.new
  0.upto(words.length - 1) do |i|
    anagrams = words.each.map { |word| word if are_anagram?(words[i], word) }.compact

    result << anagrams unless result.include? anagrams
  end
  result
end


#p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

#p combine_anagrams ['Cars', 'for', 'potatoes', 'raCs', 'four', 'scar', 'creams', 'scream']