def combine_anagrams(words)
  results = Hash.new(Array.new)
  words.each do |word|
    worddown = word.downcase.chars.sort.join
    puts(worddown)
    results[worddown] = results[worddown].push(word)
  end
  return results.values
end

