# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #   <YOUR CODE HERE>

  ordered_letters_to_words = {}

  words.each {
  	|word|
  	ordered_letters_to_words[word.downcase.split(%r{}).sort] = []
  }

  words.each {
  	|word|
  	ordered_letters_to_words[word.downcase.split(%r{}).sort].push(word)
  }

  anagram_groups = []
  ordered_letters_to_words.each {
  	|key, value|
  	anagram_groups.push(value)
  }

  return anagram_groups

end

