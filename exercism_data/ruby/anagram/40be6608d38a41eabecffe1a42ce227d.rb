class Anagram

  def initialize(word)
    @word = word
  end

  def match(possible_matches)
    possible_matches.select do |possible_match|
      possible_match.chars.sort == @word.chars.sort
    end
  end

end
