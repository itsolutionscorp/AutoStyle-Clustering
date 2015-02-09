#input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  hash = Hash.new
  words.each do |str|
     sorted = str.chars.sort.join
     if (hash[sorted]== nil)
       hash[sorted] = Array.new
     end
     arr = hash[sorted]
     arr[arr.length] = str
     hash[sorted] = arr
     arr = nil
  end
 
hash.values
end
 combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
