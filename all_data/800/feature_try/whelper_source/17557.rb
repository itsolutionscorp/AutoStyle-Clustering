def combine_anagrams(words)
  buckets = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    buckets.has_key?(key) ? ((buckets[key] << word)) : (buckets[key] = [word])
  end
  output = []
  buckets.each_value { |value| (output << value) }
  return output
end

