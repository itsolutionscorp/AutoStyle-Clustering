class Scrabble

  VALUES = {
    1  => 'AEIOULNRST',
    2  => 'DG',
    3  => 'BCMP',
    4  => 'FHVWY',
    5  => 'K',
    8  => 'JX',
    10 => 'QZ'
  }

  def initialize(word)
    @word = word
  end

  def score
    @score ||= [0].tap{ |scores|
      @word and @word.each_char do |character|
        character = character.upcase
        VALUES.each do |points, matches|
          scores << points if matches.include?(character)
        end
      end
    }.inject(&:+)
  end

  def self.score(word); new(word).score; end
end
