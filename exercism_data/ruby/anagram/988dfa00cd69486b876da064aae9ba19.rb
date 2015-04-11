class Word

  def initialize(word)
    @word = word
  end

  def different?(candidate)
    @word.downcase != candidate.downcase
  end

  def same_letters?(candidate)
    sort_letters(@word) == sort_letters(candidate)
  end

  private

  def sort_letters(word)
    word.downcase.chars.sort
  end

end

class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  private

  def anagram?(candidate)
    word.same_letters?(candidate) && word.different?(candidate)
  end

end
