def combine_anagrams(words)
  anagrams = words.group_by { |word| word.downcase.chars.sort }.values
  anagrams
end

# group_by groups matching objects into a hash
# In this example each word is first sorted so the key becomes the sorted word and the value are the words that match that 
# when sorted. By calling the values method this returns all the values as an array 