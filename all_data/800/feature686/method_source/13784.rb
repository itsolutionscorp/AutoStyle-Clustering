def combine_anagrams(words)
  result = {}
  words.collect do |w|
    key = w.downcase.chars.sort.join
    result.has_key?(key) ? ((result[key] << w)) : (result[key] = [w])
  end
  return result.values
end