class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    word.casecmp(@subject).nonzero? and sorted_letters(word) == sorted_letters(@subject)
  end

  def sorted_letters(word)
    word.downcase.chars.sort
  end
end
