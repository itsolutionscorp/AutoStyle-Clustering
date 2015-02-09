def combine_anagrams(words)
  return words if ((words == nil) or (words.length < 2))
  results = Hash.new { |h, k| h[k] = Array.new }
  words.each do |word|
    worddown = word.to_s.downcase.chars.sort.join
    results[worddown].push(word)
    results[worddown]
  end
  return results.values
end