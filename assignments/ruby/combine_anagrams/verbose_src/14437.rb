#
# part3.rb
# Mark Koenig
# 6/4/2012
#

# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

# sort an array of strings into anagram groups
def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    if not hash.key? key
      hash[key] = [word]
    else
      hash[key].push word
    end
  end
  hash.values
end
