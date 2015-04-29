class Anagram
  def initialize(word)
    @word = word.upcase
  end

  def match(list)
    list.each_with_object([]) do |item, matchlist|
      matchlist << item if anagram?(item.upcase)
    end
  end

  private

  attr_reader :word

  def anagram?(second_word)
    return false if word == second_word

    word.chars.sort == second_word.chars.sort
  end
end
