class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.split('').sort
  end

  def match(word_list)
    word_list.select { |w| letters_of_words_match?(w) }
  end

  private

  def letters_of_words_match?(second_word)
    word == second_word.split('').sort
  end
end
