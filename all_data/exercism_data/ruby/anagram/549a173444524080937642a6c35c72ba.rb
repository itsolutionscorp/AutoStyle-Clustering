class Anagram

  def initialize(word)
    @word = word
  end

  def match(choices)
    choices.select(&anagram?)
  end

  private

  def anagram?
    (matches? & !identical?).to_proc
  end

  def matches?
    ->(other) { alphagram.matches? other }
  end

  def identical?
    ->(other) { word.casecmp(other).zero? }
  end

  def alphagram
    @alphagram ||= Alphagram.new(word)
  end

  attr_reader :word

end

class Proc
  def & other
    ->(x) { call(x) & other.call(x) }
  end

  def !
    ->(x) { !call(x) }
  end
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
