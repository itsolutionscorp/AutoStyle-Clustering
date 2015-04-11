class Anagram
  def initialize(target)
    @target = target
  end

  def match(list)
    list.select { |word| anagram?(word.downcase) }
  end

  private

  def anagram?(word)
    different_words?(word) && characters_match?(word)
  end

  def different_words?(word)
    !@target.eql?(word)
  end

  def characters_match?(word)
    @target.downcase.chars.sort == word.downcase.chars.sort
  end
end
