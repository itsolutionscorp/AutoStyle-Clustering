class Scrabble
  LETTER_VALUES = {
    1 => 'AEIOULNRST',
    2 => 'DG',
    3 => 'BCMP',
    4 => 'FHVWY',
    5 => 'K',
    8 => 'JX',
    10 => 'QZ'
  }


  ## Initialize lookup
  CHAR_VALUE_MAP = Hash.new(0)
  LETTER_VALUES.each{|value, letters|
    letters.chars.each{|l| CHAR_VALUE_MAP[l] = value}
  }

  def initialize(word)
    @word = word || ''
  end

  def score
    sum = @word.chars.reduce(0) do |sum, char|
      sum +  CHAR_VALUE_MAP[char.upcase]
    end
  end

  def self.score(word)
    new(word).score
  end
end
