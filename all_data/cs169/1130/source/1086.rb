### Part III - anagrams
def are_anagrams(word1,word2)
  if word1.length == word2.length
    return word1.downcase.chars.sort==word2.downcase.chars.sort
  end
  return false
end

def combine_anagrams(words)
  result = Array.new
  oldWords = Array.new(words)
  while oldWords.length != 0 do
    word = oldWords[0]
    oldWords.delete_at 0
    subResult = [word]
    i=0
    while i < oldWords.length do
      if are_anagrams(oldWords[i],word)
        subResult = subResult+ [oldWords[i]]
        oldWords.delete_at i
      end
      i = i+1
    end
    result = result + [subResult]
  end
  return result
end
