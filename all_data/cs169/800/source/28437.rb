#
# Part 3: anagrams
#
# input: 
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]] 
# HINT: you can quickly tell if two words are anagrams by sorting their 
#  letters, keeping in mind that upper vs lowercase doesn't matter 
 
def combine_anagrams(words) 
  h = Hash.new
  r = []
  words.each do |i|
    j = i.downcase.chars.sort.join
    if h.has_key? j
      if h.has_value?(i) == false; h[j] += [i]; end
    else
      h[j] = [i]
    end
  end
  h.each_value { |value| r << value }
  return r     
end 

#
# Test anagrams
#
p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

