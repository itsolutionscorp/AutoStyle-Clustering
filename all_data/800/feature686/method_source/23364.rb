def combine_anagrams(words)
  hash = Hash.new
  hash.default = Array.new
  words.map do |x|
    key = x.split("").sort.join
    hash[key] += [x]
  end
  hash.values
end