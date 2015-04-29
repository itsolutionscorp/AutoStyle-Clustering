# NOTE: The String#sum method will give you an
# unique number for the same letters (case sensitive).
# Use that to get the anagrams
def combine_anagrams(words)
  words.inject({ }) do |hash, word|
    hash[word.downcase.sum] ||= []
    hash[word.downcase.sum] << word
    hash
  end.values
end

p combine_anagrams([])
