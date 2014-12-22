def combine_anagrams(words)
  result = []
  while words.length != 0
    word = words[0].downcase.chars.sort
    anagrams = words.select { |s| s.downcase.chars.sort == word }    
    words.reject! { |s| anagrams.include? s }
    result << anagrams
  end
  result
end