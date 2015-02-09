def combine_anagrams(words)
  hasher = {}
  anagrams = []
  num = 0
  words.each do |word|
    sorted_word = word.chars.sort { |a, b| a.casecmp(b) }.join
    if hasher.has_key?(sorted_word.downcase) then
      (anagrams[hasher[sorted_word.downcase]] << word)
    else
      hasher[sorted_word.downcase] = num
      anagrams[num] = [word]
      num = (num + 1)
    end
  end
  return anagrams
end