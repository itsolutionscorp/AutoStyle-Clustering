CHAR_SCORES = Hash.new(0)
{
  %w{A E I O U L N R S T} => 1,
  %w{D G} => 2,
  %w{B C M P} => 3,
  %w{F H V W Y} => 4,
  %w{K} => 5,
  %w{J X} => 8,
  %w{Q Z} => 10,
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
      word.upcase.split('').inject(0) do |sum, character|
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
