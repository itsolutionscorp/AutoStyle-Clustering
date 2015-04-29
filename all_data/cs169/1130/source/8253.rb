def combine_anagrams(words)
  magic_hash = Hash.new {|h, k| h[k] = Array.new}
  words.reduce(magic_hash) {|hash, word| hash[word.downcase.each_char.to_a.sort.join] << word; hash}
  magic_hash.values
end
