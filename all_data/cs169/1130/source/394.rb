
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)

  anagrams = Hash.new

  words.each do |word|
    k = word.downcase.split(//).sort.join
    if anagrams.has_key?(k)
      anagrams[k] = (anagrams[k] << word)
    else
      anagrams[k] = [word]
    end
  end

  return ( anagrams.values )

end

# input:
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect

