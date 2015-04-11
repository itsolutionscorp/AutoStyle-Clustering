class Anagram
  attr_reader :word_chars, :word

  def initialize(word)
    @word = word.downcase
    @word_chars = sorted_chars(@word)
  end

  def match(candidates)
    candidates.select { |c| is_anagram?(c) }
  end

  private

  def is_anagram?(candidate)
    normalized = candidate.downcase
    sorted_chars(normalized) == word_chars && normalized != word
  end

  def sorted_chars(word)
    word.chars.sort
  end
end
