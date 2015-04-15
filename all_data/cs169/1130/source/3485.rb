# not sure if this can be made shorter?
def combine_anagrams(words)
  # Create an array of the words with their letters in sorted order.
  swords = words.map { |w| w.downcase.split(//).sort.join }
  # Group elements by the sorted strings, returning only the values.
  words.group_by { |s| swords[words.index(s)] }.values
end

# combine_anagrams(%w(cars for potatoes racs four scar creams scream))
combine_anagrams(%w(stop pots Ogre spot gore leap opts peal))
