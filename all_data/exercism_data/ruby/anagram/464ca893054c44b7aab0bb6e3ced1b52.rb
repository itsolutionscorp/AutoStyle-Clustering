class Anagram
  def initialize(subject)
    @subject = Word.new(subject.downcase)
  end

  def match(words)
    words.select{|word| subject_matches?(Word.new(word)) }
  end

  private

  def subject_matches?(word)
    use_the_same_chars?(word) && non_identical?(word)
  end

  def use_the_same_chars?(word)
    word.the_same_chars?(@subject)
  end

  def non_identical?(word)
    word != @subject
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
