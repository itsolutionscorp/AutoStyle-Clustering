def combine_anagrams(words)
  signatures = {}
  words.each do |word|
    (signatures[word.downcase.chars.sort.join] ||= []) << word
  end
  signatures.values
end

# input: 
words = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

p combine_anagrams(words)