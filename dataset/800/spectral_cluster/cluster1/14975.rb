# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  bases = {}
  words.each do |wrd|
    base = wrd.downcase.chars.sort.join
    if bases.has_key? base
      bases[base] += [wrd]
    else
      bases[base] = [wrd]
    end
  end
  bases.values
end

#print combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
