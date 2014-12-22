def combine_anagrams(words)
  anagrams = Hash.new 
  result = Array.new
  words.each do |word|
    anagram = word.downcase.chars.sort.join
    if anagrams.has_key?(anagram)
      result[anagrams[anagram]].push(word)
    else
      anagrams[anagram] = result.length
      result.push(Array.new(1, word))
    end
  end
  return result
end