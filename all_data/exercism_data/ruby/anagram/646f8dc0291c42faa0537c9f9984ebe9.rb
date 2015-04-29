class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| Matcher.new(word, candidate).match? }
  end

  private

  attr_reader :word

  class Matcher
    attr_reader :word, :candidate

    def initialize(word, candidate)
      @word, @candidate = word, candidate
    end

    def match?
      same_size? && same_letters? && !normalized_identical?
    end

    private

    def same_size?
      candidate.size == word.size
    end

    def same_letters?
      normalized_letters(candidate) == normalized_letters(word)
    end

    def normalized_identical?
      candidate.downcase == word.downcase
    end

    def normalized_letters(s)
      s.downcase.chars.sort
    end
  end
end
