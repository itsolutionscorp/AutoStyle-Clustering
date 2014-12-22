# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  hash = {}
  words.each do |word|
    letters = word.downcase.chars.sort.join
    if hash.has_key?(letters)
      hash[letters] += [word]
    else
      hash[letters] = [word]
    end
  end
  hash.values

end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])