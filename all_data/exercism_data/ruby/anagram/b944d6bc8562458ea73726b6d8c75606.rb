class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(list)
    list.find_all do |test_word|
      Word.new(test_word).anagram_of? word
    end
  end

  class Word < Struct.new(:value)
    attr_reader :letters

    def initialize(string)
      super(string.downcase)
      @letters = Letters.new(value)
    end

    def anagram_of?(other)
      other != self && other.letters == letters
    end
  end

  class Letters < Struct.new(:value)
    def initialize(string)
      super(string.chars.sort)
    end
  end
end
