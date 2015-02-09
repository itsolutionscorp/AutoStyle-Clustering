# Part 3
p "----- Part 3 -----"
def combine_anagrams(words)
  sorted_words = Hash.new
  words.each do |word|
    sorted_word = word.downcase.split(//).sort.join
    sorted_words[sorted_word] = Array.new unless sorted_words.has_key?(sorted_word)
    sorted_words[sorted_word] << word
  end
  sorted_words.values
end
  
  p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
  