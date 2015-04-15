def combine_anagrams(words)
  tempWords = words.sort
  hashWords = Hash.new
  tempWords.each do |tempString|
    stringSortedChars = sorted_char_array(tempString)
    if hashWords.has_key?(stringSortedChars) then
      tempArray = Array.new
      tempArray = hashWords.fetch(stringSortedChars)
      tempArray.push(tempString)
    else
      tempArray = Array.new
      tempArray[0] = tempString
      hashWords[stringSortedChars] = tempArray
    end
  end
  arrayResult = hashWords.values
  return arrayResult
end

def sorted_char_array(tempString)
  tempArray = Array.new
  i = 0
  tempString.each_char do |tempChar|
    tempArray[i] = tempChar.downcase
    i = (i + 1)
  end
  tempArray = tempArray.sort
  strSortedCharString = ""
  tempArray.each do |charAtIndex|
    strSortedCharString = (strSortedCharString + charAtIndex)
  end
  return strSortedCharString
end

