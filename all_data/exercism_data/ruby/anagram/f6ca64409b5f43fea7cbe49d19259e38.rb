class Anagram
  def initialize(subject)
    @subject = subject.downcase
  end

  def match(words)
    words.select{|word| subject_matches?(word) }
  end

  private

  def subject_matches?(word)
    AnagramMatcher.new(Word.new(@subject), Word.new(word)).match?
  end

end

class AnagramMatcher
  def initialize(subject, word)
    @subject = subject
    @word    = word
  end

  def match?
    use_the_same_chars? && non_identical?
  end

  private

  def non_identical?
    @word != @subject
  end

  def use_the_same_chars?
    @word.the_same_chars?(@subject)
  end
end

class Word
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def ==(other_word)
    string.downcase == other_word.string.downcase
  end

  def the_same_chars?(other_word)
    string.chars.sort == other_word.string.chars.sort
  end
end
