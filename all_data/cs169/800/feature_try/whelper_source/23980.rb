def combine_anagrams(words)
  kv = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    kv.has_key?(key) ? (kv[key] = (kv[key] + [word])) : (kv[key] = [word])
  end
  return kv.values
end

