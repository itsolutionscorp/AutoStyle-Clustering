class Anagram
  attr_reader :word

  def initialize initial_word
    @word = initial_word
  end

  def match possible_words
    possible_words.select do |possible_match|
      word.sort_characters == possible_match.sort_characters and not same_words? word, possible_match
    end
  end

  def same_words? first_word, second_word
    first_word.downcase == second_word.downcase
  end
end

class String
  def sort_characters
    self.downcase.chars.sort
  end
end
