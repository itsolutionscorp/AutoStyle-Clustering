class Anagram
  def initialize(content)
    @content = content.downcase
  end

  def anagram_of?(other)
    self.letters == other.letters &&
      self.content != other.content
  end

  def match words
    words.select do |word|
      anagram_of?(Anagram.new(word))
    end
  end

  protected

  attr_reader :content

  def letters
    @letters ||= @content.downcase.chars.sort
  end
end
