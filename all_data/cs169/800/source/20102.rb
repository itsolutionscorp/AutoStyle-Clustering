#!/usr/local/bin/ruby
def combine_anagrams (words)
  h = Hash.new {|k,v| k[v] = [] }
  words.each { |w| h[w.downcase.chars.sort.join] << w }
  return h.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).to_s
