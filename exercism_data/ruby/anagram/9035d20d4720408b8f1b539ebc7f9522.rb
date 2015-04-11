class Anagram

  def initialize(word)
    @word = word
  end

  def match(choices)
    choices.select(&anagram?)
  end

  private

  def anagram?
    ->(other) { alphagram.matches?(other) && word.downcase != other.downcase }
  end

  def alphagram
    @alphagram ||= Alphagram.new(word)
  end

  attr_reader :word

end

class Alphagram

  def initialize(word)
    @word = word
  end

  def value
    @value ||= word.downcase.chars.sort
  end

  def matches?(word)
    Alphagram.new(word).value == value
  end

  private

  attr_reader :word
end
