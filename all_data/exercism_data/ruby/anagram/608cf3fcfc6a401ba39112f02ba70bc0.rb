class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)
    Comparator.new(words, anagram).evaluate
  end

  private
  attr_reader :anagram
end

class Comparator
  def initialize(words, anagram)
    @words, @anagram = words, anagram
  end

  def evaluate
    words.delete_if { |word| identical?(word) }.
          keep_if   { |word| cipher?(word)    }
  end

  def identical?(string)
    string.downcase == anagram.downcase
  end

  def cipher?(string)
    sequencer(string) == sequencer(anagram)
  end

  private
  attr_reader :words, :anagram

  def sequencer(string)
    string.downcase.chars.sort.join
  end
end
