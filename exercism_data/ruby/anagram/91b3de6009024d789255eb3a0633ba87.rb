class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
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
    alphagram == other.alphagram
  end

  protected

  def normalized
    @subject.downcase
  end

  def alphagram
    normalized.chars.sort
  end
end
