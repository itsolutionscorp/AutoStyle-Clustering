def combine_anagrams(words)
  anagram_hash = Hash.new(0)
  words.each do |word| 
    hash_el = anagram_hash[word.chars.sort.join].clone
    anagram_hash[word.chars.sort.join] = (hash_el << word) if hash_el
  end 
  anagram_hash.each_value.inject([]){|rem, next_val| rem << next_val}
end
