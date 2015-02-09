def combine_anagrams(words)
  returnArray = []
  list = words.clone
  while (list.length > 0) do
    iList = list.clone
    i = 1
    listIndex = 0
    curArray = []
    (curArray << iList[0])
    list.delete_at(0)
    while (i < iList.length) do
      if is_anagram?(curArray[0], iList[i]) then
        (curArray << iList[i])
        list.delete_at(listIndex)
      else
        listIndex = (listIndex + 1)
      end
      i = (i + 1)
    end
    (returnArray << curArray)
  end
  return returnArray
end