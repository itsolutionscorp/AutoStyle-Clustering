class Scrabble

  SCORES = {
    1 => %w[ A E I O U L N R S T ],
    2 => %w[ D G ],
    3 => %w[ B C M P ],
    4 => %w[ F H V W Y ],
    5 => %w[ K ],
    8 => %w[ J X ],
    10 =>%w[ Q Z ]
  }

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @letters = word.to_s.strip.upcase.split('')
  end

  def score
    @letters.reduce(0) { | sum, letter | sum + score_for_letter(letter) }
  end

  private

  def score_for_letter(letter_to_match)
    score = 0
    SCORES.each_pair do |category_value, category_letters|
      score = category_value if category_letters.include?(letter_to_match)
    end
    score
  end

end
