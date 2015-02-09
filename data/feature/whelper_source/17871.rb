def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    anagrams[key] = Array.new if (not anagrams.has_key?(key))
    anagrams[key].push(word)
  end
  result = Array.new
  anagrams.each { |group| result.push(group[1]) }
  return result
end

