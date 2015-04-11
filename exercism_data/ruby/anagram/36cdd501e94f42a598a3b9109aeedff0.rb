class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(words)
    words.select {|word| anagram?(word) }
  end

  private

  def alphagram(word)
    word.downcase.chars.sort.join
  end

  def anagram?(word)
    alphagram(word)
    alphagram(subject) == alphagram(word) && subject.downcase != word.downcase
  end
end
