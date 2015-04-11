class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)
    Evaluator.new(words, anagram).evaluate
  end

  private
  attr_reader :anagram
end

class Evaluator
  def initialize(words, anagram)
    @words, @anagram = words, anagram
  end

  def evaluate
    words.delete_if { |word| Comparator.identical?(word, anagram) }.
          keep_if   { |word| Comparator.cipher?(word, anagram)    }
  end

  private
  attr_reader :words, :anagram
end

class Comparator
  def self.identical?(this, that)
    this.downcase == that.downcase
  end

  def self.cipher?(this, that)
    sequencer(this) == sequencer(that)
  end

  private

  def self.sequencer(string)
    string.downcase.chars.sort.join
  end
end
