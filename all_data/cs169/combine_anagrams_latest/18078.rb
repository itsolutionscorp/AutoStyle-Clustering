# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  h = Hash.new()

  words.each do |word|
    lowercase_word = word.downcase
    sorted_lowercase_word = lowercase_word.chars.sort.join

    similar_words = h[sorted_lowercase_word]
    if not similar_words
      similar_words = []
    end
    similar_words << word
    h[sorted_lowercase_word] = similar_words

  end

  output = []
  h.each_value { |value| output << value }
  return output
end

#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#output = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

#p combine_anagrams(input)
#p "-----"
#p output
