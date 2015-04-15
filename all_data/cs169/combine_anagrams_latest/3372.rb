def combine_anagrams(words)
  hash = {}
  words.each do |word|
    key = word.downcase.split(//).sort.to_s
    if !hash.has_key?(key)
      hash[key] = []
    end
    hash[key] = hash[key].push(word)
  end
  hash.values
end
