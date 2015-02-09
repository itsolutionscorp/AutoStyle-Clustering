def combine_anagrams(words)
  match = Array.new(0)
  anagram = Array.new(0)
  ordered_array = Array.new(0)
  copy_ordered_array = Array.new(0)
  for i in words do
    (word_array = Array.new(0)
    word_array = i.split(/\s*/)
    count = 0
    for r in word_array do
      (word_array[count] = r.downcase
      count = (count + 1))
    end
    ordered_array[ordered_array.length] = Array.new(0)
    ordered_array[(ordered_array.length - 1)][0] = word_array.sort.join
    ordered_array[(ordered_array.length - 1)][1] = i)
  end
  for i in ordered_array do
    copy_ordered_array[copy_ordered_array.length] = i
  end
  for i in ordered_array do
    (for j in copy_ordered_array do
      match[match.length] = j[1] if (i[0] == j[0])
    end
    if (match.length > 0) then
      anagram[anagram.length] = match
      for h in match do
        for q in copy_ordered_array do
          copy_ordered_array.delete(q) if (h == q[1])
        end
      end
      match = Array.new(0)
    end)
  end
  return anagram.sort
end