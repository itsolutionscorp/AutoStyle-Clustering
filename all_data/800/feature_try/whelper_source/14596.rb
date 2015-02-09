def combine_anagrams(words)
  anagrams = Hash.new
  listOfAnagramWords = Array.new
  words.each do |word|
    sorted_word = word.chars.sort_by(&:downcase).join.downcase
    puts(sorted_word)
    if anagrams.has_key?(sorted_word) then
      anagrams[sorted_word].insert(-1, word)
    else
      anagrams[sorted_word] = [word]
    end
  end
  anagrams.each_key { |key| listOfAnagramWords.insert(-1, anagrams[key]) }
  return listOfAnagramWords
end

