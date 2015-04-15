class Anagram
  attr_reader :word

  def initialize(word)
    @word = sort_letters(word)
  end

  def match(word_list)
    word_list.select { |w| is_anagram_of?(w) }
  end

  private

  def is_anagram_of?(second_word)
    word == sort_letters(second_word)
  end

  def sort_letters(word)
    word.split('').sort
  end
end
