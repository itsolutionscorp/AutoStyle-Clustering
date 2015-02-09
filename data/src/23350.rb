def combine_anagrams(words)
  output = nil
  hash = Hash.new
  words.each do |word|
    charsSorted = word.downcase.chars.sort.join
    if hash.has_key?(charsSorted) then
      hash[charsSorted] = (hash.fetch(charsSorted) << word)
    else
      hash[charsSorted] = [word]
    end
  end
  hash.keys.each do |key|
    if (output == nil) then
      output = [hash.fetch(key)]
    else
      (output << hash.fetch(key))
    end
  end
  return output
end