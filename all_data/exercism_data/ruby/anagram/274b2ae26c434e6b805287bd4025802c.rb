class Anagram
  def initialize(word)
    @word = Word.new word
  end

  def match(words)
    words.select { |candidate| @word.anagram?(Word.new candidate) }
  end

  private

  class Word
    def initialize(word)
      @original = word
    end

    def normalized
      @original.downcase
    end

    def fingerprint
      @original.downcase.chars.sort
    end

    def different?(candidate)
      normalized != candidate.normalized
    end

    def anagram?(candidate)
      different?(candidate) && candidate.fingerprint == fingerprint
    end
  end
end
