def combine_anagrams(words)
  hash = {}
  words.each do |w|
    key = w.downcase.chars.sort.to_s
    container = hash[key]
    container = (container == nil) ? ([w]) : ((container << w))
    hash[key] = container
  end
  hash.values
end

