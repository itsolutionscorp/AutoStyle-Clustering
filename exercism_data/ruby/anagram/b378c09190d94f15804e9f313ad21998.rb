class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def anagrams(words)
    words.select do |word|
      anagram(word) && not_identical(word)
    end
  end

  def not_identical(word)
    word.downcase != subject.downcase
  end

  def anagram(word)
    word.downcase.chars.sort == subject.downcase.chars.sort
  end

  def match(words)
    anagrams(words)
  end
end
