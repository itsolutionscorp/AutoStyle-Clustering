class Anagram
  def initialize(stem)
    @stem = stem
  end

  def match(list)
    list.select { |word| WordPair.new(word, stem).anagram? }
  end

  private

  attr_reader :stem

  class WordPair
    def initialize(word, stem)
      @word = word.downcase
      @stem = stem.downcase
    end

    def anagram?
      different_words? && use_same_characters?
    end

    private

    attr_reader :word, :stem

    def different_words?
      word != stem
    end

    def use_same_characters?
      word.chars.sort == stem.chars.sort
    end
  end
end
