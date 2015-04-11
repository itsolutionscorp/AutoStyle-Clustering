class Anagram
  def initialize(base_word)
    @base_word = base_word.downcase
  end

  def match(candidate_words)
    candidate_words.select { |w|
      anagram?(prepare_word_for_comparison(w))
    }
  end

  private

  def anagram?(candidate_word)
    candidate_word != base_word &&
      candidate_word.chars.sort == base_word_chars
  end

  def prepare_word_for_comparison(word)
    word.downcase
  end

  attr_reader :base_word

  def base_word_chars
    @base_word_chars ||= base_word.chars.sort
  end
end
