class Scrabble
  def initialize word
    @word = word.to_s
  end

  def score
    @word.chars.map(&:value).inject(0, :+)
  end

  def self.score word
    new(word).score
  end
end

class String
  SCORES = {
    %w(a e i o u l n r s t) => 1,
    %w(d g) => 2,
    %w(b c m p) => 3,
    %w(f h v w y) => 4,
    %w(k) => 5,
    %w(j x) => 8,
    %w(q z) => 10
  }

  def value
    key = SCORES.keys.find { |k| k.include? self.downcase }
    SCORES[key] || 0
  end
end
