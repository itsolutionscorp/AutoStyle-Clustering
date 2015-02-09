# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words=[])
  return [] if words.empty?
  hash = {}
  words.each do |word|
    anagram = word.downcase.split("").sort.join("")
    if hash[anagram].nil? then
      hash[anagram]=[word]
    else
      hash[anagram].push(word)
    end
  end
  return hash.values
end

input=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
output=combine_anagrams(input)
puts output.inspect
