class Scrabble
  SCORES = {
    %w(A E I O U L N R S T) => 1,
    %w(D G)                 => 2,
    %w(B C M P)             => 3,
    %w(F H V W Y)           => 4,
    %w(K)                   => 5,
    %w(J X)                 => 8,
    %w(Q Z)                 => 10
  }.reduce({}) do |memo, (letters, score)|
    letters.each { |letter| memo[letter] = score }

    memo
  end

  attr_reader :word

  def initialize(word)
    @word = word.to_s.strip.upcase
  end

  def score
    word.each_char.inject(0) do |memo, letter|
      memo += SCORES[letter]

      memo
    end
  end

  def self.score(word)
    new(word).score
  end
end
