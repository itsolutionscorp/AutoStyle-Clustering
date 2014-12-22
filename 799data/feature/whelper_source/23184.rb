def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.to_s
    if anagrams.has_key?(key) then
      anagrams[key] = (anagrams[key] + [word])
    else
      anagrams[key] = [word]
    end
  end
  return anagrams.values
end

