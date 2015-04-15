def combine_anagrams(words)
  new = {}
  words.each do |word|
    sorted =  word.downcase.chars.sort
    if new.has_key?sorted
      new[sorted] = new[sorted] << word
    else
      new[sorted] = [word]
    end
  end
  return new.values
end


input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts combine_anagrams(input)