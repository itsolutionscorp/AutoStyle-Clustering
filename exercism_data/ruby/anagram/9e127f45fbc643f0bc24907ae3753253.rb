class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = sort_letters(word)
  end

  def match(word_list)
    word_list.select { |w| anagram?(w) }
  end

  private

  def anagram?(second_word)
    letters == sort_letters(second_word)
  end

  def sort_letters(word)
    word.split('').sort
  end
end
