class Word
  attr_reader :raw

  def initialize(raw)
    @raw = raw.downcase
  end

  def letters
    @raw.chars
  end

  def fingerprint
    letters.sort
  end

  def ==(other)
    raw == other.raw
  end

  def is_anagram_of?(other)
    (self.fingerprint == other.fingerprint) && 
     self != other
  end
end

class Anagram
  def initialize(original_word)
    @original_word = Word.new(original_word)
  end

  def match(words)
    words.select do |w|
      target_word = Word.new(w)
      target_word.is_anagram_of?(@original_word)
    end
  end
end
