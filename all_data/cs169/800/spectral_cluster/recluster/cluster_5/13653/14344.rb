class String
  def wordsize
    size = 0
    self.each_byte { |c| size += c }
    return size
  end
end


def combine_anagrams(words)
  anagrams = []
  @words   = words.map  { |word| word.downcase }
  @word_s  = @words.map { |word| word.wordsize }
  @word_s.each do |size|
    anag = []
    @words.map { |word| anag << words[@words.index(word)] if word.wordsize == size }
    anagrams << anag
  end
  return anagrams.uniq
end
