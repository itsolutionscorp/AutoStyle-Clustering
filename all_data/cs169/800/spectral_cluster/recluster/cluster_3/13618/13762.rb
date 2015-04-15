def combine_anagrams(words)
  wordsCopy = []
  resultList = []
  words.each do |word|
    wordsCopy << (word.downcase.chars.sort.join)
  end
  
  index = 0
  while (index < words.length) do
    findSortedWord = wordsCopy[index];
    if (findSortedWord != "")
      wordArray = []
      findIndex = wordsCopy.index(findSortedWord)
      while (findIndex != nil) do
        wordArray << words[findIndex]
        wordsCopy[findIndex] = ""
        findIndex = wordsCopy.index(findSortedWord)
      end
      resultList << wordArray
    end
    index += 1
  end
  return resultList
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

