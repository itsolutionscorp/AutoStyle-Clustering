class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.find_all do |list_word|
      word == list_word
    end
  end

  class Word < String
    def same_length?(other)
      length == other.length
    end

    def same_letters?(other)
      chars = self.chars.sort
      other_chars = other.chars.sort
      chars == other_chars
    end

    def ==(other)
      same_length?(other) && same_letters?(other)
    end
  end
end