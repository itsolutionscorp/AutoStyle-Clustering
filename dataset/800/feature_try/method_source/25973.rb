def combine_anagrams(words)
  results = Hash.new { |hash, key| hash[key] = Array.new }
  words.each do |word|
    newWord = word.downcase.chars.sort.join
    results[newWord].push(word)
  end
  return results.values
end