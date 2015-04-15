class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(list)
    list.find_all do |test_word|
      test_word = Word.new(test_word)
      test_word != word && test_word.letters == word.letters
    end
  end

  class ValueObject
    attr_reader :value

    def ==(other)
      other.value == value
    end
  end

  class Word < ValueObject
    attr_reader :letters

    def initialize(string)
      @value = string.downcase
      @letters = Letters.new(value)
    end
  end

  class Letters < ValueObject
    def initialize(string)
      @value = string.chars.sort
    end
  end
end
