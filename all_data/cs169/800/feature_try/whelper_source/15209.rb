def anagrams?(w1, w2)
  (w1.downcase.split("").sort == w2.downcase.split("").sort)
end

def combine_anagrams(words)
  ret = []
  while (words.length > 0) do
    i = 0
    temp = [words[i]]
    j = (i + 1)
    while (j < words.length) do
      if anagrams?(words[i], words[j]) then
        (temp << words[j])
        words.delete_at(j)
      else
        j = (j + 1)
      end
    end
    (ret << temp)
    words.delete_at(i)
  end
  ret
end

