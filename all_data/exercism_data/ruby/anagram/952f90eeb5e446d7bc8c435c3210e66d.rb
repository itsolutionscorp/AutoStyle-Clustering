class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word.downcase
  end

  def match(test_words)
    test_words.select {|potential_anagram| anagram?(potential_anagram)}
  end

  def anagram?(potential_anagram)
    same_chars?(potential_anagram) && !identical_to_subject?(potential_anagram)
  end

  def same_chars?(word)
    word.downcase.chars.sort == subject.chars.sort
  end

  def identical_to_subject?(word)
    word.downcase == subject
  end
end
