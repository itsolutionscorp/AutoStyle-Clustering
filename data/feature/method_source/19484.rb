def combine_anagrams(words)
  rslt = Array.new
  words.each do |word|
    ind = -1
    i = 0
    rslt.each do |arr|
      ind = i if (arr[0].downcase.chars.sort == word.downcase.chars.sort)
      i = (i + 1)
    end
    if (ind == -1) then
      rslt[rslt.size] = Array.new
      (rslt[(rslt.size - 1)] << word)
    else
      (rslt[ind] << word)
    end
  end
  return rslt
end