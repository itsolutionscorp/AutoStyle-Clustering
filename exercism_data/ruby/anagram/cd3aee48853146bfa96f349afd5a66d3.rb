Anagram = Struct.new(:word) do
  def match(words)
    words.select { |x| anagram_of?(x) }
  end

  private

  def anagram_of?(other_word)
    alphabetize(other_word) == alphabetize(word)
  end

  def alphabetize(item)
    item.chars.sort
  end
end
