def combine_anagrams(words)
  wordsCopy = []
  resultList = []
  words.each { |word| (wordsCopy << word.downcase.chars.sort.join) }
  index = 0
  while (index < words.length) do
    findSortedWord = wordsCopy[index]
    if (findSortedWord != "") then
      wordArray = []
      findIndex = wordsCopy.index(findSortedWord)
      while (findIndex != nil) do
        (wordArray << words[findIndex])
        wordsCopy[findIndex] = ""
        findIndex = wordsCopy.index(findSortedWord)
      end
      (resultList << wordArray)
    end
    index = (index + 1)
  end
  return resultList
end