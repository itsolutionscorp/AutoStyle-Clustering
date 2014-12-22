def isAnagram?(word1, word2)
  (word1.downcase.split("").sort == word2.downcase.split("").sort)
end

def combine_anagrams(words)
  array = []
  while (words.length > 0) do
    i = 0
    temp = [words[i]]
    j = (i + 1)
    while (j < words.length) do
      if isAnagram?(words[i], words[j]) then
        (temp << words[j])
        words.delete_at(j)
      else
        j = (j + 1)
      end
    end
    (array << temp)
    words.delete_at(i)
  end
  return array
end

