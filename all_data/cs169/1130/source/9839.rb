def combine_anagrams(words)
  dict = {}
  words.each do |word|
    anagram = word.downcase.chars.sort.join
    if dict.include? anagram
      dict[anagram] = dict[anagram] + [word]
    else
      dict[anagram] = [word]
    end
  end

  anagrams = []
  dict.each do |anagram, words|
    anagrams += [words]
  end

  anagrams
end