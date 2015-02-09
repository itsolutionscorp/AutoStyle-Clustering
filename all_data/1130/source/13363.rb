def combine_anagrams(words)
  if not words.empty?
    word = words.shift()
    anagrams = [word] + words.select {|potential_anagram|
      word.downcase.chars.sort.join == potential_anagram.downcase.chars.sort.join
    }
    [anagrams].concat(combine_anagrams(words - anagrams))
  else
    []
  end  
end
