def combine_anagrams(words)
  results = Hash.new { |h, k| h[k] = Array.new }
  words.each do |word|
    worddown = word.downcase.chars.sort.join
    results[worddown] = results[worddown].push(word)
  end
  return results.values
end