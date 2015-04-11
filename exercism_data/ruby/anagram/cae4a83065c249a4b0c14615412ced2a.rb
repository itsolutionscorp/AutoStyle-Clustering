class Anagram
  attr_reader :word, :letters

  def initialize(word)
    @word = word.downcase
    @letters = word.downcase.chars.sort
  end

  def match(list)
    list.select do |candidate|
      candidate = candidate.downcase
      candidate != word &&
        letters == candidate.chars.sort
    end
  end
end
