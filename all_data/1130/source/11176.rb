def combine_anagrams(words)
  hashwords = Hash.new([])
  words.each do |word|
    hashwords[word.downcase.chars.sort.join] += [word] 
  end
  arraywords = []
  hashwords.each do |key,word|
    arraywords += [word]
  end
  arraywords
end
