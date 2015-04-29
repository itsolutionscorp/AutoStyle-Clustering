CHAR_SCORES = Hash.new(0)
{
  "AEIOULNRST".chars => 1,
  "DG".chars => 2,
  "BCMP".chars => 3,
  "FHVWY".chars => 4,
  "K".chars => 5,
  "JX".chars => 8,
  "QZ".chars => 10,
}.each do |characters, score|
  characters.each do |character|
    CHAR_SCORES[character] = score
  end
end

class Scrabble

  def initialize(word)
    @word = word
  end

  def self.score(word)
    unless word.nil?
      word.upcase.chars.inject(0) do |sum, character|
        sum += CHAR_SCORES[character]
      end
    else
      0
    end
  end

  def score
    self.class.score(@word)
  end

end
