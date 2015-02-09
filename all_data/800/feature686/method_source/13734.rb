def combine_anagrams(words)
  anagrams_hash = Hash.new
  words.each do |w|
    key = w.downcase.split("").sort.join
    anagrams_hash[key] = Array.new unless anagrams_hash.has_key?(key)
    anagrams_hash[key].push(w)
  end
  return anagrams_hash.values
end