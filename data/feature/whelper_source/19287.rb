def combine_anagrams(words)
  anagramsGroup = []
  words.length.times do |i|
    anagrams = []
    (anagrams << words[i]) unless words[i].empty?
    words.length.times do |j|
      if (i != j) and ((words[i].downcase != words[j].downcase) and ((words[j] != "") and (words[i].chars.sort { |a, b| a.casecmp(b) }.join == words[j].chars.sort { |a, b| a.casecmp(b) }.join))) then
        (anagrams << words[j])
        words[j] = ""
      end
    end
    (anagramsGroup << anagrams) unless anagrams.empty?
  end
  return anagramsGroup
end

