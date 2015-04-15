class Scrabble
  CHAR_SCORES = Hash.new(0)
  {
    "AEIOULNRST" => 1,
    "DG" => 2,
    "BCMP" => 3,
    "FHVWY" => 4,
    "K" => 5,
    "JX" => 8,
    "QZ" => 10,
  }.each do |characters, score|
    characters.chars.each do |character|
      CHAR_SCORES[character] = score
    end
  end

  def initialize(word)
    @word = word
  end

  def self.score(word)
    word ||= ""
    word.upcase.chars.map {|character| CHAR_SCORES[character]}.inject(0, &:+)
  end

  def score
    self.class.score(@word)
  end

end
