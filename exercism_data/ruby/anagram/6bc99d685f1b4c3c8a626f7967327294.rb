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
      same_size? && same_letters? && !same_word?
    end

    private

    def same_size?
      compare(:size)
    end

    def same_letters?
      compare(:normalized_letters)
    end

    def same_word?
      compare(:downcase)
    end

    def compare(method)
      if candidate.respond_to?(method) && word.respond_to?(method)
        candidate.send(method) == word.send(method)
      else
        send(method, candidate) == send(method, word)
      end
    end

    def normalized_letters(s)
      s.downcase.chars.sort
    end
  end
end
