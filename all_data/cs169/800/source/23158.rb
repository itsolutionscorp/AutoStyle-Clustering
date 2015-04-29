# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# # => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# # letters, keeping in mind that upper vs lowercase doesn't matter
 def combine_anagrams(words)
 # <YOUR CODE HERE>
  result = {}
  words.each do |word| 
    key = word.downcase.split('').sort.join
    result[key] ||= [] 
    result[key] << word
  end
  result.values
 end

