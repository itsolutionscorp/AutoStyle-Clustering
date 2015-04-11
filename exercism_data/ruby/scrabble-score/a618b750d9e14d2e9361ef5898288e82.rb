class Scrabble
  @@scores = {
    %w(a e i o u l n r s t) => 1,
    %w(d b)                 => 2,
    %w(b c m p)             => 3,
    %w(f h v w y)           => 4,
    %w(k)                   => 5,
    %w(j x)                 => 8,
    %w(q z)                 => 10
  }

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word.downcase
  end

  def score
    return 0 unless @word

    @word.chars.reduce(0) do |score, letter|
      require 'pry'; binding.pry
      score += @@scores.detect { |letters, _| letters.include?(letter) }.value
    end
  end
end
