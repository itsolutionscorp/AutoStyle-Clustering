def combine_anagrams(words)
  anagrams = []
  @words = words.map { |word| word.downcase }
  @word_s = @words.map { |word| word.wordsize }
  @word_s.each do |size|
    anag = []
    @words.map do |word|
      (anag << words[@words.index(word)]) if (word.wordsize == size)
    end
    (anagrams << anag)
  end
  return anagrams.uniq
end