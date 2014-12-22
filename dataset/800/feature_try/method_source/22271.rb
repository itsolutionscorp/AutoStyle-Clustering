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