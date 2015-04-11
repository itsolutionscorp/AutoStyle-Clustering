class Anagram
  def initialize(word)
    @word = word
  end

  attr_reader :word

  def match(words)
    words.grep(self)
  end

  def ===(anagram)
    self == anagram && normalized != Anagram(anagram).normalized
  end

  def ==(anagram)
    sorted == Anagram(anagram).sorted
  end

  protected

  def normalized
    @normalized ||= word.downcase
  end

  def sorted
    @sorted ||= normalized.chars.sort
  end
end

def Anagram(arg)
  return arg if arg.is_a?(Anagram)
  Anagram.new(arg.to_str)
end
