class Anagram
  def initialize(subject)
    @subject = subject.downcase
  end

  def match(words)
    words.select { |word| anagram?(word.downcase) }
  end

  private

  def anagram?(word)
    word != @subject and sorted_letters(word) == sorted_letters(@subject)
  end

  def sorted_letters(word)
    word.chars.sort
  end
end
