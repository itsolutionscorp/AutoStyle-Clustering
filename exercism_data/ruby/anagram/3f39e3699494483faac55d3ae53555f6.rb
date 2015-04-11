class Anagram
  attr_reader :word

  def initialize(word)
    @word = split(word)
  end

  def match(possible_matches)
    possible_matches.select {|possible_match| word_matches?(possible_match)}
  end

  def word_matches?(possible_match)
    word == split(possible_match)
  end

  def split(word)
    word.split("").sort
  end
end
