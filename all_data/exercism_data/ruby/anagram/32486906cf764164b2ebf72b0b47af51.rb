class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)
    words.delete_if { |word| identical?(word) }.keep_if { |word| cipher?(word) }
  end

  private
  attr_reader :anagram

  def identical?(word)
    Comparator.identical?(word, anagram)
  end

  def cipher?(word)
    Comparator.cipher?(word, anagram)
  end
end

class Comparator
  def self.identical?(this, other)
    new(this, other).identical?
  end

  def self.cipher?(this, other)
    new(this, other).cipher?
  end

  def initialize(this, other)
    @this, @other = this, other
  end

  def identical?
    this.downcase == other.downcase
  end

  def cipher?
    sequencer(this) == sequencer(other)
  end

  private
  attr_reader :this, :other

  def sequencer(string)
    string.downcase.chars.sort.join
  end
end
