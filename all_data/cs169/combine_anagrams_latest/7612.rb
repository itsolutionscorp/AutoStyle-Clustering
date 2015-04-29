# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  anagrams = {}
  words.each do |word| 
    w = word.downcase.chars.sort.join
    if anagrams[w] == nil
      anagrams[w] = []
    end
    anagrams[w] += [word]
  end
  return anagrams.values
end

puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
