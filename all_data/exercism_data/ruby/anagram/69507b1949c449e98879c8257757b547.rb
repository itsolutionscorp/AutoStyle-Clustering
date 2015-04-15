class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = sort_letters(word)
  end

  def match(word_list)
    word_list.select { |w| anagram?(w) }
  end

  private

  def anagram?(word)
    letters == sort_letters(word)
  end

  def sort_letters(word)
    word.split('').sort
  end
end
