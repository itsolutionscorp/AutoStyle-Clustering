def combine_anagrams(words)
  anagramas = Hash.new
  final_array = Array.new
  words.each do |word|
    word1 = word.split(//).sort.join
    word1.downcase!
    anagramas[word1] = Array.new if (anagramas[word1] == nil)
    (anagramas[word1] << word)
  end
  anagramas.each { |key, value| (final_array << value) }
  return final_array
end

