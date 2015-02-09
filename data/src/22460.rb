def combine_anagrams(words)
  finalArray = []
  words.each do |s|
    tempWord = s.to_s.downcase.chars.sort.join
    checkBit = false
    finalArray.each do |item|
      if (tempWord == item[0].to_s.downcase.chars.sort.join) then
        (item << s)
        checkBit = true
      end
    end
    (finalArray << [s]) if (checkBit == false)
  end
  return finalArray
end