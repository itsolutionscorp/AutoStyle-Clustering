class Anagram
  attr_reader :word

  def initialize(word)
    @word = EncodedWord.new(word)
  end

  def match(possibilities)
    possibilities.select { |possibility| word.anagram_of? possibility }
  end

  class EncodedWord
    attr_reader :original, :encoded

    def initialize(word)
      @original = word.downcase
      @encoded = encode original
    end

    def anagram_of?(word)
      word = word.downcase
      word != original and encode(word) == encoded
    end

    private

    def encode(word)
      word.chars.sort.join
    end
  end
end
