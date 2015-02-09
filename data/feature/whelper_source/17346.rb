def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    if (word.length == 1) then
      key = word.downcase
    else
      if key = word.downcase.chars.sort.join then
        # do nothing
      end
    end
    if anagrams.has_key?(key) then
      anagrams[key].push(word)
    else
      anagrams[key] = [word]
    end
  end
  anagrams.values
end

