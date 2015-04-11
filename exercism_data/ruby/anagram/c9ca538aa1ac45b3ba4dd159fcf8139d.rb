class Anagram
  def initialize(stem)
    @stem = Validator.new(stem)
  end

  def match(list)
    list.select { |word| stem.anagram_for?(word) }
  end

  private

  attr_reader :stem

  class Validator
    def initialize(stem)
      @stem = stem.downcase
    end

    def anagram_for?(word)
      different_words?(word) && use_same_characters?(word)
    end

    private

    attr_reader :stem

    def different_words?(word)
      word != stem
    end

    def use_same_characters?(word)
      word.chars.sort == stem.chars.sort
    end
  end
end
