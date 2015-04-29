def combine_anagrams(words)
  anagrams = {}
  words.each do |i|
    anagram_key = i.chars.sort { |a, b| a.casecmp(b) }.join.downcase
    if anagrams.has_key?(anagram_key) then
      anagrams[anagram_key].push(i)
    else
      anagrams[anagram_key] = [i]
    end
  end
  anagrams.values
end

