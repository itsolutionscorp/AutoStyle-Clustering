class Anagram
  def self.try_convert(word)
    word.is_a?(self) ? word : new(word)
  end

  protected attr_reader :word

  def initialize(word)
    @word = word.chars.sort
  end

  def match(words)
    words.select do |word|
      self.word == self.class.try_convert(word).word
    end
  end
end
