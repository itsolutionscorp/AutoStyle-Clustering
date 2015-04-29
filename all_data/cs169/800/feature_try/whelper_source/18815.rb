def combine_anagrams(words)
  output = Array.new
  included = Array.new
  i = words.size
  for x in (0..(i - 2)) do
    (word = words[x]
    if (not included.include?(word.downcase.chars.sort.join)) then
      included.push(word.downcase.chars.sort.join)
      o = Array.new
      o.push(word)
    else
      next
    end
    for y in ((x + 1)..(i - 1)) do
      (word2 = words[y]
      if (word.downcase.chars.sort.join == word2.downcase.chars.sort.join) then
        o.push(word2)
      end)
    end
    output.push(o))
  end
  return output
end

