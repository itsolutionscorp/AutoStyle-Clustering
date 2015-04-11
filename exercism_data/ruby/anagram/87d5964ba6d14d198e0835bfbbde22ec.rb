class Anagram
  attr_reader :target
  def initialize(target)
    @target = target
  end

  def match(possibilities)
    possibilities.select {|possibility| anagram?(possibility, target)}
  end

  def anagram?(word, other_word)
    as_sorted_array(word) == as_sorted_array(other_word)
  end

  def as_sorted_array(str)
    str.chars.sort
  end
end
