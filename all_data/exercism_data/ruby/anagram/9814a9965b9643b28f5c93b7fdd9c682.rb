class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(contenders)
    contenders.select do |contender|
      possible_anagram = contender.downcase
      word != possible_anagram && word.chars.sort == possible_anagram.chars.sort
    end
  end
end
