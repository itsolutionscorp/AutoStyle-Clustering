class AnagramWord
  def initialize(content)
    @content = content
  end

  def ==(other)
    self.identity == other.identity
  end

  protected

  def identity
    @identity ||= @content.downcase.chars.sort
  end
end

class Anagram
  def initialize word
    @word = AnagramWord.new(word)
  end

  def match words
    words.select { |word| @word == AnagramWord.new(word) }
  end
end
