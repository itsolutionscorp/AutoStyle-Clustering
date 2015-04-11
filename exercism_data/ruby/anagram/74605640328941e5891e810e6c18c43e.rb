class Anagram
  def initialize(word)
    @word = word.downcase
    @chars = @word.chars.sort
  end

  def match(words)
    words.select { |w| anagram?(format_word(w)) }
  end

  private

  def anagram?(word)
    word.size == @word.size &&
      word != @word &&
      word.chars.sort == @chars
  end

  def format_word(word)
    word.downcase
  end
end
