class Anagram
  def initialize(word)
    @word = word.upcase
  end

  def match(list)
    list.select { |item| anagram?(item.upcase) }
  end

  private

  attr_reader :word

  def anagram?(second_word)
    return false if word == second_word

    word.chars.sort == second_word.chars.sort
  end
end
