def combine_anagrams(wordList)
  anagrams = Hash.new()

  wordList.each do |w|
    anagrams[w.downcase.chars.sort.join] = Array.new() if anagrams[w.downcase.chars.sort.join].nil?
    anagrams[w.downcase.chars.sort.join] = anagrams[w.downcase.chars.sort.join].push w
  end

  return anagrams.values
end
