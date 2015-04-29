def combine_anagrams(words)
  arr = Array.new
  result = Array.new
  words.each do |w|
    arr << w.downcase.chars.sort.join
  end
  for i in 0..words.length
    tmp = []
    test = arr[i]
    for j in i..words.length
      if (arr[j] != nil) and (test == arr[j])
        tmp <<  words[j]
        arr[j] = nil
      end
    end
    if tmp != []
      result.push(tmp)
    end
  end
  return result
end