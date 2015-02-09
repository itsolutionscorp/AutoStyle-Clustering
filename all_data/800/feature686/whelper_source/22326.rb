def combine_anagrams(words)
  cache = Hash.new
  words.each do |word|
    anagram = word.downcase.chars.sort.join
    if cache.has_key?(anagram) then
      (cache[anagram] << word)
    else
      cache[anagram] = [word]
    end
  end
  cache.values
end

