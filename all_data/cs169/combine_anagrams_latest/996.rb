def combine_anagrams(words)
  anagramsArray = Array.new
  words.map do |word|
    word.downcase.chars.sort.to_s
  end.sort.uniq.each do |word|
    anagramsArray << words.select {|match| match.downcase.chars.sort.to_s==word}
  end
  anagramsArray
end
