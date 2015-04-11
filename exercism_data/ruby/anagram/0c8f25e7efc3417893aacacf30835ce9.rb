class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input
  end

  def match(potential_words)
    potential_words.select do |word|
      anagram_to_subject?(word)
    end
  end

private
  def anagram_to_subject?(word)
    word.chars.sort == subject.chars.sort
  end
end
