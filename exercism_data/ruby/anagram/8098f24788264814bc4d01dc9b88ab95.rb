class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(candidates)
    candidates.select do |candidate|
      other = Word.new(candidate)
      word != other && word.anagram_of?(other)
    end
  end

  private

  attr_reader :word
end

class Word
  def initialize(word)
    @word = word
  end

  def ==(other)
    word.downcase == other.word.downcase
  end

  def anagram_of?(other)
    sanitized_chars == other.sanitized_chars
  end

  protected

  attr_reader :word

  def sanitized_chars
    word.downcase.chars.sort
  end
end
