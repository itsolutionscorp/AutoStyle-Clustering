def is_anagram?(x, y)
  return x.downcase.chars.sort.join == y.downcase.chars.sort.join
end

def combine_anagrams(words)
  returnArray = []
  list = words.clone
  while list.length > 0
    iList = list.clone
    i = 1
    listIndex = 0
    curArray = []
    curArray << iList[0]
    list.delete_at(0)
    while i < iList.length
      if is_anagram?(curArray[0],iList[i])
        curArray<<iList[i]
        list.delete_at(listIndex)
      else
        listIndex = listIndex + 1
      end
      i = i + 1
    end
    returnArray << curArray
  end
  return returnArray
end