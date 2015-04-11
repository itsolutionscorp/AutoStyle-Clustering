class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    words.select{ |word| letters == letters_from(word) }
  end

  private

  def letters_from(word)
    word.split("").sort
  end
end
