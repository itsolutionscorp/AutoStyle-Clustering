# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their 
# letters, keeping in mind that upper vs lowercase doesn't matter

def normalize(word)
  return word.downcase.split(//).sort.to_s
end


def combine_anagrams(words) 
  groups = Hash.new{|hash, key| hash[key] = []}
  words.each { |word| 
    groups[normalize(word)] <<= word
  }
    
  retval = []
  groups.each_value {|val| retval <<= val}
  return retval
end
