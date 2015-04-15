class Scrabble
  LETTER_VALUES = { 1 => "aerioulnrst",
                    2 => "dg",
                    3 => "bcmp",
                    4 => "fhvwy",
                    5 => "k",
                    8 => "jx",
                   10 => "qz" }

  def initialize(word)
    @word = word
  end

  def score
    Scrabble.score(@word)
  end

  def self.score(word)
    return 0 if word.nil?
    word.downcase.chars.inject(0) do |x, char|
      x + self.letter_value(char)
    end
  end

  def self.letter_value(char)
    score = LETTER_VALUES.find { |ary| ary[1].chars.include? char }
    score.nil? ? 0 : score[0]
  end
end
