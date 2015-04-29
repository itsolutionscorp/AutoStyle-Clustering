def combine_anagrams(words)
  wordHash = {}
  words.each do |word|
    normalized = word.downcase.chars.sort.join
    if ! wordHash.has_key? normalized
      wordHash[normalized] = [word]
    else
      wordHash[normalized] += [word]
    end
  end
  wordHash.values
end

# puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
