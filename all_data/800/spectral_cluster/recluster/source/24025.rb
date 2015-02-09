# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def order(string)
  string.downcase.split(//).sort.join
end

def combine_anagrams(words)
  words_mod = Array.new #to store lowercase words with sorted characters
  words.each {|w| words_mod.push(order(w))}
  words_uniq = words_mod.uniq # Get only unique words
  word_groups = Array.new # To store groups of anagrams
  words_uniq.each do
  |current_word|
    similar_words = Array.new
    words_mod.each_index {|i| w = words_mod[i]; similar_words.push(i) if w == current_word}
    word_groups.push(similar_words)
  end

  anagrams = Array.new
  word_groups.each_index {|i| anagrams << word_groups[i].map {|j| words[j]}}
  anagrams
end

string = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
print combine_anagrams(string)