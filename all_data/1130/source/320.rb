# HINT: you can quickly tell if two words are anagrams by sorting their 
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  hash = Hash.new{[]}
  words.each do |w|
    sorted = w.chars.sort { |a, b| a.casecmp(b) } .join.downcase
    hash[sorted] = hash[sorted] << w
  end
  hash.values
end

# input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]] 
# p combine_anagrams(input)

# input = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creAms', 'scream'] 
# p combine_anagrams(input)
# [["creAms", "scream"], ["potatoes"], ["four"], ["Cars", "racs", "scar"], ["for"]]
