module ScrabbleHelper
  refine String do
    def score
      case self
      when /[aeioulnrst]/i then 1
      when /[dg]/i         then 2
      when /[bcmp]/i       then 3
      when /[fhvwy]/i      then 4
      when /[k]/i          then 5
      when /[jx]/i         then 8
      when /[qz]/i         then 10
      else 0
      end
    end
  end
end

class Scrabble
  using ScrabbleHelper

  def self.score(word)
    new(word).score
  end

  def  initialize(word)
    @word = word || String.new
  end

  def score
    @word.chars.reduce(0) { |total, letter| total + letter.score }
  end
end
