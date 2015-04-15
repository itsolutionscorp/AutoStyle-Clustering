class Anagram
  def initialize(string)
    @string = string
  end

  attr_reader :string

  def match(strings)
    Array(strings).grep(self)
  end

  def ===(other)
    self == other && normalized != Anagram(other).normalized
  end

  def ==(other)
    sorted == Anagram(other).sorted
  end

  protected

  def normalized
    @normalized ||= string.downcase
  end

  def sorted
    @sorted ||= normalized.chars.sort
  end
end

def Anagram(arg)
  return arg if arg.is_a?(Anagram)
  Anagram.new(arg.to_str)
end
