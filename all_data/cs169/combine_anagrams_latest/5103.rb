def combine_anagrams(words = [])
  @anagrams = []
  def already_in_a_group?(word)
    @anagrams.flatten.include? word.downcase
  end

  def anagram?(word1,word2)
    word1.chars.sort_by(&:downcase).join == word2.chars.sort_by(&:downcase).join ? true : false
  end

  def return_anagrams(word,array)
    group = []
    unless already_in_a_group? word
      array.each do |word_array|
        group.push word_array.downcase if anagram? word, word_array
      end
    end
    return group
  end
  words.each do |word|
    a = return_anagrams word, words
    @anagrams.push a unless a.empty?
  end
  return @anagrams.inspect
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts combine_anagrams()