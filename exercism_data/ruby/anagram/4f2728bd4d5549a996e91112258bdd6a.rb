Anagram = Struct.new(:word) do
  def match(words)
    words.select { |x| matches?(x) }
  end

  private

  def matches?(other_word)
    @sorted_word ||= word.chars.sort
    other_word.chars.sort == @sorted_word
  end
end
