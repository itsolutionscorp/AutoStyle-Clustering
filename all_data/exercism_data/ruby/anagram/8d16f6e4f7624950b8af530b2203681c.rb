class Anagram
  def initialize original
    self.original = original
  end

  def match candidates
    self.candidates = candidates
    anagrams.map &:to_s
  end

  private

  attr_reader :original, :candidates

  def anagrams
    candidates.find_all { |candidate| candidate == original }
  end

  def original= original
    @original = AnagramWord.new original
  end

  def candidates= candidates
    @candidates = candidates.map { |candidate| AnagramWord.new candidate }
  end

  class AnagramWord
    include Comparable

    attr_reader :word

    def initialize word
      @word = word.downcase
    end

    def letters
      word.chars.sort
    end

    def == other
      other.class == self.class && other.word != self.word && other.letters == self.letters
    end

    def to_str
      word
    end
    alias :to_s :to_str
  end
end
