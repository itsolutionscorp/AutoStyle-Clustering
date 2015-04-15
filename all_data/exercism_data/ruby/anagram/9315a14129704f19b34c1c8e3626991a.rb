class Anagram
  def initialize(word)
    @word = AnagramWord.new(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      AnagramWord.new(possible_anagram).is_anagram_of?(word)
    end
  end

  attr_accessor :word

  class AnagramWord
    def initialize(word)
      @normalized_word = normalize_word(word)
      @sorted_characters = sort_characters(normalized_word)
    end

    def is_anagram_of?(other_anagram_word)
      !is_identical?(other_anagram_word) && has_same_characters?(other_anagram_word)
    end

    attr_accessor :normalized_word, :sorted_characters

    private

    def normalize_word(word)
      word.downcase
    end

    def sort_characters(word)
      word.chars.to_a.sort
    end

    def is_identical?(other_anagram_word)
      normalized_word == other_anagram_word.normalized_word
    end

    def has_same_characters?(other_anagram_word)
      sorted_characters == other_anagram_word.sorted_characters
    end

  end

end
