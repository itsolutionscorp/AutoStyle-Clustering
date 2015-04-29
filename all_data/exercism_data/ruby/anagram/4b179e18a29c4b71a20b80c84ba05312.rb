class Anagram

  def initialize(string)
    @word = Word.new(string)
  end

  def match(test_cases)
    test_cases.select {|string| valid_anagram?(string) }
  end

  def valid_anagram?(string)
    Word.new(string).anagram_of?(@word)
  end
end

class Word < Struct.new(:string)

  def anagram_of?(other)
    self != other &&
      chars.sort == other.chars.sort
  end

  def chars
    string.downcase.each_char
  end

  def ==(other)
    string.downcase == other.string.downcase
  end
end
