class Anagram
  def initialize(base_word)
    @base_word = base_word.downcase
    @base_word_chars = @base_word.chars.sort
  end

  def match(candidate_words)
    candidate_words.select { |w| anagram?(format_word(w)) }
  end

  private

  def anagram?(candidate_word)
    candidate_word != @base_word &&
      candidate_word.chars.sort == @base_word_chars
  end

  def format_word(word)
    word.downcase
  end
end
