def combine_anagrams(words)
  h = Hash.new{ | hash, key| hash[key] = [] }
  words.each do
    | word |
    h[word.downcase.chars.sort.join] << word
  end
  result = []
  h.each do
    | key, value |  result << value
  end
  result
end

=begin
require 'pp'
pp combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
=end