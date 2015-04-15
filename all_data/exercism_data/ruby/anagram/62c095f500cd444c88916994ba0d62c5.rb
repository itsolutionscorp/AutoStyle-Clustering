class Anagram
  attr_reader :original_subject

  def initialize(original_subject)
    @original_subject = original_subject
  end

  def subject
    original_subject.downcase
  end

  def match(words)
    words.select do |word|
      anagram_of?(word)
    end
  end

  protected

  def anagram_of?(word)
    detector = self.class.new(word)
    detector.chars == chars && detector != self
  end

  def chars
    subject.chars.sort
  end

  def ==(anagram)
    subject == anagram.subject
  end
end
