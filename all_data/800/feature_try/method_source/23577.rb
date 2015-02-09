def combine_anagrams(words)
  hash = {}
  words.each do |word|
    anagram_id = word.downcase.chars.sort.join
    hash[anagram_id] = [] if (not hash.has_key?(anagram_id))
    hash[anagram_id].push(word)
  end
  return hash.each_value.to_a
end