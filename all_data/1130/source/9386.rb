# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = Hash.new
  words.each do |str|
     sorted = str.chars.sort.join.downcase
     if (hash[sorted]== nil)
       hash[sorted] = Array.new
     end
     res = hash[sorted]
     res[res.length] = str
     hash[sorted] = res
     res = nil
  end
  hash.values
end
words=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(words)

