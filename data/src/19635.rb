def combine_anagrams(words)
  hash = {}
  words.each do |word|
    letters = word.downcase.chars.sort.join
    if hash.has_key?(letters) then
      hash[letters] += [word]
    else
      hash[letters] = [word]
    end
  end
  hash.values
end