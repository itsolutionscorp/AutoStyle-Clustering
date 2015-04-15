# Part 3: anagrams

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],
# ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    normalized = w.downcase.split('').sort.join
    if anagrams.has_key?(normalized)
      anagrams[normalized][anagrams[normalized].length] = w
    else
      anagrams[normalized] = [ w ]
    end
  end

  result = []
  anagrams.each_key do |normalized|
    result[result.length] = anagrams[normalized]
  end
  result
end

