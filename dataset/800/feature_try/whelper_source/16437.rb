def combine_anagrams(wordList)
  anagrams = Hash.new
  wordList.each do |w|
    if anagrams[w.downcase.chars.sort.join].nil? then
      anagrams[w.downcase.chars.sort.join] = Array.new
    end
    anagrams[w.downcase.chars.sort.join] = anagrams[w.downcase.chars.sort.join].push(w)
  end
  return anagrams.values
end

