class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |w| w if letters_of_words_match?(w) }
  end

  private

  def letters_of_words_match?(second_word)
    word.split('').sort == second_word.split('').sort
  end
end
