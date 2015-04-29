def combine_anagrams(words)
  anagram_hash = Hash.new { |hash, key| hash[key] = [ ] }
  words.each do |e|
    anagram_hash[e.downcase.chars.sort.join].push(e)
  end
  anagram_hash.values
end
