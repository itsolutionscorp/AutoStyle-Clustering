class Anagram
  def initialize(word)
    @plain_word = word.downcase
    @letters = @plain_word.chars.sort
  end

  def match(candidates)
    candidates.select { |candidate| is_anagram?(candidate) }
  end

  private

  def is_anagram?(candidate)
    !identical?(candidate) && same_letters?(candidate)
  end

  def identical?(candidate)
    candidate.downcase == @plain_word
  end

  def same_letters?(candidate)
    candidate.downcase.chars.sort == @letters
  end
end
