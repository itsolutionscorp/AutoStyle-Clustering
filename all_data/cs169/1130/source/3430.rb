# input: arr=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  h=Hash.new([])
  words.map {|w| key=w.downcase.split('').sort.join; h[key]+=[w]}
  h.values
  
end

