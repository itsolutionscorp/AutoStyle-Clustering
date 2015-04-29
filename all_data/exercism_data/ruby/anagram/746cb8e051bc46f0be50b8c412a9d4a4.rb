class Anagram
  attr_reader :letters
  def initialize(word)
    @letters = letters_of(word)
  end

  def match(word_list)
    word_list.select { |word| anagram_of?(word) }
  end

  def ==(other)
    self.letters == other.letters
  end

  private
  def anagram_of?(word)
    self == Anagram.new(word)
  end

  def letters_of(word)
    word.chars.sort
  end
end
