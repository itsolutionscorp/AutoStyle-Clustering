class Anagram

  class ComparableWord

    attr_reader :comparable_sequence

    def initialize(word)
      @comparable_sequence = word.downcase.chars.sort
    end

    def anagram?(other)
      @comparable_sequence == other.comparable_sequence
    end

  end

  def initialize(word)
    @word = ComparableWord.new(word)
  end

  def match(candidates)
    candidates.select do |candidate|
      @word.anagram?(ComparableWord.new(candidate))
    end
  end

end
