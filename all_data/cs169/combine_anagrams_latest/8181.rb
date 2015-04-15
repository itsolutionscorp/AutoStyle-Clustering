# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if(hash[sorted]==nil)
      array = Array.new
      hash[sorted]=array
    end
    array = hash[sorted]
    array = array.push(word)
    hash[sorted]=array
  end
  grouped = Array.new
  hash.each do |key,value| 
    grouped = grouped.push(value)
  end 
  return grouped
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
