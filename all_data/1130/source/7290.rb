# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = {}
  hash.default=[]
  words.each{|word|
    down_cased = word.downcase
    anagram = down_cased.chars.sort.join
    hash[anagram]= hash[anagram] + [word]
  }
  hash.values
end

puts (combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'cReams', 'scream']).inspect


