class Anagram
  attr_reader :initial_word

  def initialize(initial_word)
    @initial_word = initial_word
  end

  def match(potential_anagrams)
    anagrams = potential_anagrams.select do |potential_anagram|
      characters_of_word(potential_anagram) == initial_word_characters
    end

    anagrams.reject do |anagram|
      anagram.downcase == initial_word.downcase
    end
  end

  private

  def initial_word_characters
    @initial_word_characters ||= characters_of_word(initial_word)
  end

  def characters_of_word(word)
    word.downcase.chars.sort
  end
end
