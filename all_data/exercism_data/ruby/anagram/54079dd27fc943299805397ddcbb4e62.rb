require 'forwardable'

class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |word| @word.anagram?(Word.new(word)) }
  end

end

class Word
  extend Forwardable
  include Comparable

  def_delegator :@word, :downcase

  def initialize(word)
    @word = word
  end

  def letters
    Letters.new(self)
  end

  def ==(other)
    downcase == other.downcase
  end

  def anagram?(other)
    self != other && letters == other.letters
  end

end

class Letters

  def initialize(word)
    @chars = word.downcase.chars.sort
  end

  def ==(other)
    chars == other.chars
  end

  protected
  attr_reader :chars

end
