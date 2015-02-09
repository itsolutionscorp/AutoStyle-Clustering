def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    key = word[0].split("").sort.join
    hash.has_key?(key) ? (hash[key] += [word]) : (hash[key] = [key])
  end
  return hash.values
end