class Scrabble
  LETTER_VALUES = {"AEIOULNRST" => 1, "DG" => 2, "BCMP" => 3,
                   "FHVWY" => 4, "K" => 5, "JX" => 8, "QZ" => 10}
  
  def initialize(word)
    @word = word
  end

  def score
    Scrabble.score @word
  end

  def self.score(word)
    return 0 if word.nil?
    counter = 0
    word.upcase.chars.each do |letter|
      LETTER_VALUES.each do |letters, value|
        counter += value if letters.chars.member? letter
      end
    end
    counter
  end
end
