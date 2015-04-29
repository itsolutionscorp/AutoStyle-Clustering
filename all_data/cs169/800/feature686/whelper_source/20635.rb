def combine_anagrams(words)
  hash = {}
  anagrams = []
  words.each do |word|
    sortedWord = word.downcase.each_char.sort.join
    if hash.has_key?(sortedWord) then
      hash[sortedWord].push(word)
    else
      hash[sortedWord] = Array.new.push(word)
    end
  end
  hash.each_value { |anagramCollections| anagrams.push(anagramCollections) }
  return anagrams
end

