class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    words.select{ |word| letters.sort == letters_from(word).sort }
  end

  private

  def letters_from(word)
    word.split("")
  end
end
