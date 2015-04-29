class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
       self == Anagram.new(candidate)
    end
  end

  def ==(other)
    normalized != other.normalized and signature == other.signature
  end

  protected

  def normalized
    @word.downcase
  end

  def signature
    normalized.chars.sort
  end
end
