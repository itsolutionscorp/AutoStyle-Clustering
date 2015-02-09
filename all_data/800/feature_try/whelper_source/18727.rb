def combine_anagrams(words)
  wordHash = Hash.new
  words.each do |word|
    sortedword = word.downcase.chars.sort.join
    if wordHash[sortedword].nil? then
      newWord = [word]
      wordHash[sortedword] = newWord
    else
      wordAlreadyPresent = wordHash[sortedword]
      wordAlreadyPresent.push(word)
      wordHash[sortedword] = wordAlreadyPresent
    end
  end
  listToReturn = Array.new
  wordHash.each { |key, value| listToReturn.push(value) }
  return listToReturn
end

