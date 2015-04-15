class ScrabbleLetter
  def self.score letter
    case letter.upcase
      when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' then 1
      when 'D', 'G' then 2
      when 'B', 'C', 'M', 'P' then 3
      when 'F', 'H', 'V', 'W', 'Y' then 4
      when 'K' then 5
      when 'J', 'X' then 8
      when 'Q', 'Z' then 10
      else 0
    end
  end
end

class Scrabble

  def initialize letters
    @letters = letters || ''
  end

  def self.score letters
    Scrabble.new(letters).score
  end

  def score
    @letters.chars.reduce(0) { |sum, letter| sum + score_letter(letter) }
  end

  private

  def score_letter letter
    ScrabbleLetter.score letter
  end
end
