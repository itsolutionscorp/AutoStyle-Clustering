def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|
    key = (w.downcase.chars.sort * "")
    hash.has_key?(key) ? (hash[key] += [w]) : (hash[key] = [w])
  end
  return hash.values
end