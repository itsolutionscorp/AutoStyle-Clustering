class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(words)
    anagrams(words)
  end

  private

  def anagrams(words)
    words.select { |word| anagram?(word) }
  end

  def format(input)
    input.downcase.chars.sort
  end

  def anagram?(word)
    format(word) == format(subject) && word.downcase != subject.downcase
  end
end
