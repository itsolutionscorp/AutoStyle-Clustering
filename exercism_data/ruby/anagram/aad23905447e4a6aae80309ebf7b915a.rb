class Anagram
  def initialize(word)
    @word   = word.downcase
    @sorted = @word.chars.sort
  end

  def match(test_words)
    test_words.select { |w| anagram?(w) }
  end

  private

  def anagram?(test_word)
    ltest = test_word.downcase
    ltest != @word && ltest.chars.sort == @sorted
  end
end
