def combine_anagrams(words)
  occurance = Hash.new
  words.each do |word|
    normalWord = word.downcase.chars.sort.join
    if occurance[normalWord].nil? then
      occurance[normalWord] = [word]
    else
      occurance[normalWord].push(word)
    end
  end
  retVal = []
  occurance.each { |key, value| retVal.push(value) }
  return retVal
end