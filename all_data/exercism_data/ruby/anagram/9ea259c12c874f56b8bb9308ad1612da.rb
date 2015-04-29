class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |w| w if letters_of_words_match?(w) && word_lengths_match?(w) }
  end

  private

  def word_lengths_match?(second_word)
    word.length == second_word.length
  end

  def letters_of_words_match?(second_word)
    word.split('') - second_word.split('') == []
  end
end
