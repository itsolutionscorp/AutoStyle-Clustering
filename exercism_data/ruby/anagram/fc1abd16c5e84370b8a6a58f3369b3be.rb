class Anagram
  attr :subject

  def initialize(word)
    @subject = payload(word)
  end

  def match(words)
    words.find_all { |word| subject.eql?(payload(word)) }
  end

  private

  def payload(word)
    word.downcase.chars.sort
  end
end
