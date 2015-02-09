def combine_anagrams(words)
  anagram_hash=Hash.new
  words.each do
    |w|
    hash_key=w.downcase.split(//).sort.to_s
    if anagram_hash.has_key?(hash_key) then
      anagram_hash[hash_key]<<w
    else
      new_val=Array.new
      new_val[0]=w
      anagram_hash[hash_key]=new_val
    end
  end
  anagram_hash.values.to_a
end
