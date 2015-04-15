class Scrabble
  POINTS = {
    %w[A E I O U L N R S T] => 1,
    %w[D G]                 => 2,
    %w[B C M P]             => 3,
    %w[F H V W Y]           => 4,
    %w[K]                   => 5,
    %w[J X]                 => 8,
    %w[Q Z]                 => 10
  }

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word.to_s.upcase
  end

  def score
    letters.inject(0) { |total, letter| total + points(letter) }
  end

  private

  def points(letter)
    POINTS.each { |group, points| return points if group.include?(letter) }
    return 0
  end

  def letters
    @word.chars.to_a
  end
end
