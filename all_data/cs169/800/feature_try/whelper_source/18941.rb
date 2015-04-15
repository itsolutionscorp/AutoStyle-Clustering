def combine_anagrams(words)
  list = words
  anagrams = []
  cntGrp = 0
  for x in words do
    (xSrtd = x.chars.sort { |a, b| a.casecmp(b) }.join
    anagrams.push([x])
    for y in list do
      (ySrtd = y.chars.sort { |a, b| a.casecmp(b) }.join
      if (xSrtd == ySrtd) then
        if (x != y) then
          anagrams[cntGrp].push(y)
          list.delete_at(list.index(y))
        end
      end)
    end
    cntGrp = (cntGrp + 1))
  end
  return anagrams
end

