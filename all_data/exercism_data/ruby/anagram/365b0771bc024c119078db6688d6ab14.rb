class Anagram
  def initialize(target_word)
    @target_word = target_word.downcase
  end

  def match(words)
    words.select { |word| anagram?(word.downcase) }
  end

  private

  def anagram?(word)
    has_the_same_letters?(word) and is_different?(word)
  end

  def has_the_same_letters?(word)
    @target_word.chars.sort == word.chars.sort
  end

  def is_different?(word)
    @target_word != word
  end
end
