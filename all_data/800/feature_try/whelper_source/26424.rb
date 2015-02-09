def combine_anagrams(words)
  anagram_hash = Hash.new
  words.each do |word|
    base = word.downcase.chars.sort.join
    anagram_list = anagram_hash[base]
    if (anagram_list == nil) then
      anagram_hash[base] = word
    else
      (anagram_hash[base] << word)
    end
  end
  anagram_grouping = []
  anagram_hash.each { |key, value| (anagram_grouping << value) }
  return anagram_grouping
end

