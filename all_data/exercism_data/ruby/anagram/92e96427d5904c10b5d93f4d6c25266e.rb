class Anagram
  def initialize(word)
    @word = construct_anagram_word(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      is_anagram?(construct_anagram_word(possible_anagram))
    end
  end

  attr_accessor :word

  private

  def construct_anagram_word(word)
    AnagramWord.new(word)
  end

  def is_anagram?(possible_anagram)
    AnagramWordMatcher.new(word, possible_anagram).match?
  end

  class AnagramWordMatcher
    def initialize(first_word, second_word)
      @first_word, @second_word = first_word, second_word
    end

    def match?
      !are_identical? && have_same_characters?
    end

    private

    def are_identical?
      first_word.normalized_word == second_word.normalized_word
    end

    def have_same_characters?
      first_word.sorted_characters == second_word.sorted_characters
    end

    attr_accessor :first_word, :second_word
  end

  class AnagramWord
    def initialize(word)
      @normalized_word = normalize_word(word)
      @sorted_characters = sort_characters(normalized_word)
    end

    attr_accessor :normalized_word, :sorted_characters

    private

    def normalize_word(word)
      word.downcase
    end

    def sort_characters(word)
      word.chars.to_a.sort
    end

  end

end
