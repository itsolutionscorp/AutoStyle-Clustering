class Anagram
  attr_reader :word, :lowered, :alphagram

  def initialize(word)
    @word = word
    @lowered = word.downcase
    @alphagram = @lowered.chars.sort
  end

  def match(possibles)
    possibles.select { |possible| is_anagram?(possible) }
  end

  def is_anagram?(possible)
    p = Anagram.new(possible)
    lowered != p.lowered && alphagram == p.alphagram
  end
end
