def combine_anagrams(words)
  words.reduce(Hash.new([])) do |anagrams, aword|
    group = aword.downcase.chars.sort.join
    anagrams[group] = [*anagrams[group], aword]
    anagrams
  end.values
end

