#!/usr/bin/ruby

class Anagram
  def initialize(input)
    @input = input.downcase
  end

  def match(possible_matches)
    possible_matches.find_all() do |match_candidate|
      anagram? match_candidate.downcase
    end
  end

  def anagram?(match_candidate)
    (sorted(match_candidate) == sorted(@input)) && (match_candidate != @input)
  end

  def sorted(word)
    word.chars.sort
  end
end
