class Anagram
  def initialize(word)
    @plain_word = word.downcase
    @letters = @plain_word.chars.sort
  end

  def match(candidates)
    candidates.find_all { |candidate| is_anagram?(candidate) }
  end

  private

  def is_anagram?(candidate)
    different_from?(candidate) && same_letters?(candidate)
  end

  def identical?(candidate)
    candidate.downcase == @plain_word
  end

  def different_from?(candidate)
    !identical?(candidate)
  end

  def same_letters?(candidate)
    candidate.downcase.chars.sort == @letters
  end
end
