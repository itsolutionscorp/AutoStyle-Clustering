class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |potential_match|
      TestCase.new(word, potential_match).is_anagram?
    end
  end

end


class TestCase

  def initialize(word, potential_match)
    @word = word
    @potential_match = potential_match
  end

  def is_anagram?
    if @word == @potential_match
      return false
    elsif @word.downcase == @potential_match.downcase
      return false
    elsif @word.downcase.chars.sort == @potential_match.downcase.chars.sort
      return true
    end
  end

end
