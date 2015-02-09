def combine_anagrams(words)
  temp = words.map { |x| x.downcase }
  res = []
  comp = []
  temp.each_with_index do |x, y|
    added = false
    comp.each_with_index do |rx, ry|
      ra = rx.split("").sort
      rt = x.split("").sort
      if (ra == rt) then
        (res[ry] << words[y])
        added = true
      end
    end
    if (not added) then
      newRes = []
      (newRes << words[y])
      (res << newRes)
      (comp << x)
    end
  end
  return res
end

