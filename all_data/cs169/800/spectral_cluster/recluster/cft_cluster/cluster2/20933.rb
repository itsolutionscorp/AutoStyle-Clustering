def combine_anagrams(words)

if(words == [])
  return []
end
  array_of_anagrams = [[words[0]]]
  words = words.drop(1);
words.each do |word|
  found = 0
  word_clean = word.downcase.chars.sort.join
  array_of_anagrams.each do |line_of_anagrams|
     if line_of_anagrams[0].downcase.chars.sort.join == word_clean
        line_of_anagrams.push(word)
        found = 1
  end
  end
  if found == 0
    array_of_anagrams.push([word])
  end
end

return array_of_anagrams
end
