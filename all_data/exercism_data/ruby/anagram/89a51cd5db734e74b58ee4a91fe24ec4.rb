class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(possibles)
    possibles.select { |possible| self === Anagram.new(possible) }
  end

  def ===(other)
    word.casecmp(other.word) != 0 && normalized == other.normalized
  end

  def normalized
    @normalized = word.downcase.split('').sort.join
  end
end
