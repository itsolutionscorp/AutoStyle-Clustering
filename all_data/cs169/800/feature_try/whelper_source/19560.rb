def combine_anagrams(words)
  words_hash = {}
  results = []
  words.each do |word|
    key = word.downcase.chars.sort.join
    if words_hash.has_key?(key) then
      words_hash[key].push(word)
    else
      words_hash[key] = [word]
    end
  end
  words_hash.each { |key, value| results.push(value) }
  return results
end

