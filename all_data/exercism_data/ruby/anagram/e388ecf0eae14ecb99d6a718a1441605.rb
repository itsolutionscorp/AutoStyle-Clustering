class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      candidate = Anagram.new(candidate)
      self != candidate && self =~ candidate
    end
  end

  def ==(other)
    normalized == other.normalized
  end

  def =~(other)
    signature == other.signature
  end

  protected

  def normalized
    @word.downcase
  end

  def signature
    normalized.chars.sort
  end
end
