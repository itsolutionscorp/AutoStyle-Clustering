def combine_anagrams(words)
  anagrams = []
  anagramsInserted = {}
  words.each do |word|
    wordDown = word.downcase
    wordSortted = wordDown.chars.sort_by(&:downcase).join
    if anagramsInserted.has_key?(wordSortted) then
      position = anagramsInserted[wordSortted]
      anagrams[position].push(word)
    else
      newArray = []
      newArray.push(word)
      position = anagrams.length
      anagramsInserted[wordSortted] = position
      anagrams.push(newArray)
    end
  end
  anagrams
end