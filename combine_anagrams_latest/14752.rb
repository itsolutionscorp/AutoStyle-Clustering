# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = Hash.new
  words.each{|w|
    key = w.downcase.chars.sort * ""
    if hash.has_key?(key)
      hash[key] += [w]
    else
      hash[key] = [w]
    end}
    return hash.values
end

puts combine_anagrams(['Cars', 'For', 'Potatoes', 'Racs', 'Four','Scar', 'Creams', 'Scream']).inspect
