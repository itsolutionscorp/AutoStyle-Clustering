def combine_anagrams(words)
  result = {}
  words.collect {|w| key = w.downcase.chars.sort.join; result.has_key?(key) ?  result[key] << w : result[key] = [w] }
  return result.values
end
