class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(possible_matches)
    possible_matches.select { |possible_match| anagram?(possible_match) }
  end

  def anagram?(word)
    to_letters(word) == to_letters(subject) unless same_word?(word)
  end

  def same_word?(word)
    format(word) == format(subject)
  end

  def to_letters(word)
    format(word).split("").sort
  end

  def format(word)
    word.downcase
  end
end
