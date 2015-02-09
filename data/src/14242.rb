def combine_anagrams(words)
  if (words.length > 0) then
    result = [[words[0]]]
    for i in (1..(words.length - 1)) do
      (added = 0
      for j in (0..(result.length - 1)) do
        if (words[i].downcase.chars.sort.join == result[j][0].downcase.chars.sort.join) then
          result[j] += [words[i]]
          added = 1
        end
      end
      result = (result + [[words[i]]]) if (added == 0))
    end
    result
  else
    result = []
  end
end