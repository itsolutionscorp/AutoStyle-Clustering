# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
# 'scream']
# #  => output:  [["cars", "racs", "scar"], ["four"], ["for"],
# ["potatoes"],
# ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting
# their
# #  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  hash = Hash.new(0)
  words.each { |x|
    key = x.downcase.chars.sort.join
    if !hash.has_key?(key)
      hash[key] = [x]
    else
      hash[key] = hash[key] + [x]
    end
  }
  return hash.values
end