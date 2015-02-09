# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],   ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  temp_hash = Hash.new
  words.each  {    |word|
    temp_hash[word.downcase.chars.sort.join] = Array.new unless temp_hash.key?(word.downcase.chars.sort.join)
    temp_hash[word.downcase.chars.sort.join] << word
  }
  result = Array.new
  temp_hash.each{ |key,value| result << value}

  return result
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
