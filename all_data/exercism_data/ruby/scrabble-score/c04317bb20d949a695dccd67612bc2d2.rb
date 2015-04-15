class Scrabble
  def initialize(word)
    @word = word || ""
  end

  def score
    word.chars.map { |letter| Tiles.score_for(letter) }.reduce(0, :+)
  end

  def self.score(word)
    new(word).score
  end

private
  attr_reader :word
end

class Tiles
  COMPACT_SCORES = {
    %w(a e i o u l n r s t) => 1,
    %w(d g) => 2,
    %w(b c m p) => 3,
    %w(f h v w y) => 4,
    %w(k) => 5,
    %w(j, x) => 8,
    %w(q z) => 10
  }

  def self.score_for(letter)
    denormalized_scores[letter.downcase]
  end

private
  def self.denormalized_scores
    @denormalized_scores ||=
      COMPACT_SCORES.each_with_object(Hash.new(0)) { |(letters, score), hash|
        letters.each { |letter| hash[letter] = score }
      }
  end
end
