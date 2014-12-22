
def combine_anagrams(words)
  anagrams_list = {}
  words.each do |word|
    anagrams = []
    word_chars = word.downcase.chars.sort
    words.each do |other_word|
      other_word_chars = other_word.downcase.chars.sort
      if word_chars == other_word_chars then
        anagrams.push(other_word)
      end
    end
    anagrams_list[word_chars.join] = anagrams
  end
  return anagrams_list.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
