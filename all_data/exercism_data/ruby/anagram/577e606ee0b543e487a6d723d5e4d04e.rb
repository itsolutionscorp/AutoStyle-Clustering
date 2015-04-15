class Anagram
  attr_reader :word

  def initialize(word)
    @word = split(word)
  end

  def match(possible_matches)
    answer = Array.new
    possible_matches.each do |possible_match|
      answer << possible_match if word_matches?(possible_match)
    end
    answer
  end

  def word_matches?(possible_match)
    word == split(possible_match)
  end

  def split(word)
    word.split("").sort
  end
end
