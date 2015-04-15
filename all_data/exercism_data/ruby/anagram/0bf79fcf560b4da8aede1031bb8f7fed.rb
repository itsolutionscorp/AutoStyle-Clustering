class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = sort_letters(word)
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

private

  def anagram?(candidate)
    sort_letters(candidate) == letters
  end

  def sort_letters(word)
    word.split(//).sort
  end
end
