require 'pp'
# input:
x1=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
h = {}
words.each { |w|
  z = w.downcase.gsub(/^a-z/,'').chars.sort.join
  if not h.has_key?(z) then h[z] = [] end
  h[z] << w
}
return h.values()
end

#pp combine_anagrams(x1)
#puts combine_anagrams(x1)
