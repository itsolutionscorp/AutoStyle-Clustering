#!/usr/bin/ruby

class Anagram
  def initialize(input)
    @input = input
  end

  def match(possible_matches)
    possible_matches.each_with_object([]) do |match_canidate, matches|
      if anagram? match_canidate
        matches << match_canidate
      end
    end
  end

  def anagram?(match_canidate)
    (arrayed(match_canidate) == arrayed(@input)) && (match_canidate.downcase != @input.downcase)
  end

  def arrayed(word)
    word.downcase.chars.sort
  end
end
