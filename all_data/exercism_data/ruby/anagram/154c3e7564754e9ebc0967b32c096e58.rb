class Anagram

  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(words)
    (anagrams(words))
  end

  private

  def anagrams(words)
    words.select do |word|
      matching?(word) && word.downcase != subject.downcase
    end
  end

  def matching?(word)
    word.downcase.chars.sort == subject.downcase.chars.sort
  end
end
