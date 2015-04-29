def combine_anagrams(words)
  counter = 0
  anaList = Array.new
  firstArray = Array.new
  for i in (0...words.length) do
    if (i == 0) then
      firstArray[0] = words[i]
      anaList[0] = firstArray
    else
      for j in (0...anaList.length) do
        (mf = false
        if is_anagram(words[i], anaList[j][0]) then
          add_word(anaList[j], words[i])
          mf = true
          break
        end)
      end
      if (not mf) then
        nextArray = Array.new
        nextArray[0] = words[i]
        anaLn = anaList.length
        anaList[anaLn] = nextArray
      end
    end
  end
  return anaList
end

def is_anagram(testWord, baseWord)
  return false if (testWord.length != baseWord.length)
  a = testWord.downcase.scan(/[a-zA-Z]/).sort
  b = baseWord.downcase.scan(/[a-zA-Z]/).sort
  if (a == b) then
    return true
  else
    return false
  end
end

def add_word(arrayIn, word)
  len = arrayIn.length
  arrayIn[len] = word
  return arrayIn
end

