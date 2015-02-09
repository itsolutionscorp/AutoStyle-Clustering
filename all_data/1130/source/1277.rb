def combine_anagrams(words)
  hash = {}
  words.each do |word|
    norm = word.downcase.split(//).sort.join
    array = hash[norm] || []
    array << word
    hash[norm] = array
  end
  hash.values
end

p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
