# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  h = Hash.new([])
  
  words.each do |word| 
  	w = word.downcase.chars.sort.join
  	h[w] += [word] 
  end
  h.values
end