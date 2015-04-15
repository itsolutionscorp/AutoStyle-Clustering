class Anagram

  attr_reader :word, :letters

  def initialize(input)
    @word = input
    @letters = deconstruct(input)
  end

  def match(words)
    words.select do |word|
      deconstruct(word) == letters
    end
  end

  def deconstruct(input)
    input.chars.sort
  end

end
