def combine_anagrams(words)
  counter = 0
  anaList = Array.new
  firstArray = Array.new
  for i in 0...words.length
    # puts words[i]
    if (i == 0)
      firstArray[0] = words[i]
      anaList[0] = firstArray
    else
      for j in 0...anaList.length
        mf = false
        if(is_anagram(words[i], anaList[j][0]))
          add_word(anaList[j], words[i])
          mf = true
          break
        end
      end
      if (!mf)
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
  if (testWord.length != baseWord.length)
    return false
  end
  a = testWord.downcase.scan(/[a-zA-Z]/).sort
  b = baseWord.downcase.scan(/[a-zA-Z]/).sort
  if (a == b)
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

# print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
