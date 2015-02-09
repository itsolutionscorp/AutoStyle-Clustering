def combine_anagrams(words)
  arr = Array.new
  result = Array.new
  words.each { |w| (arr << w.downcase.chars.sort.join) }
  for i in (0..words.length) do
    (tmp = []
    test = arr[i]
    for j in (i..words.length) do
      if (arr[j] != nil) and (test == arr[j]) then
        (tmp << words[j])
        arr[j] = nil
      end
    end
    result.push(tmp) if (tmp != []))
  end
  return result
end