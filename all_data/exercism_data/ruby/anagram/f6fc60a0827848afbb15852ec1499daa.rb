Anagram = Struct.new(:word_to_match) do
  def match(words)
    words.reject { |word| identical?(word) }.select { |word| anagram?(word) }
  end

  private

  def identical?(word)
    word.downcase == word_to_match.downcase
  end

  def anagram?(word)
    sorted_word_to_match == sort(word)
  end

  def sorted_word_to_match
    @sorted_word_to_match ||= sort(word_to_match)
  end

  def sort(word)
    word.downcase.chars.sort
  end
end
