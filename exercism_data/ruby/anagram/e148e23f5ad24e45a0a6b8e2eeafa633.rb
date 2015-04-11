Anagram = Struct.new(:word) do
  def match(words)
    words.select { |x| anagram_of?(x) }
  end

  private

  def anagram_of?(other_word)
    other_word.chars.sort == sorted_word
  end

  def sorted_word
    # lazy initialization of @sorted_word
    @sorted_word ||= word.chars.sort
  end
end
