class Anagram
  def initialize(word)
    @word = Word.new word
  end

  def match(words)
    words.select { |word| @word.anagram?(word) }
  end

  private

  class Word
    def initialize(word)
      @original = word
    end

    def anagram?(word)
      candidate = Word.new word
      different?(candidate) && candidate.fingerprint == fingerprint
    end

    def normalized
      @original.downcase
    end

    def fingerprint
      @original.downcase.chars.sort
    end

    private

    def different?(candidate)
      normalized != candidate.normalized
    end
  end
end
