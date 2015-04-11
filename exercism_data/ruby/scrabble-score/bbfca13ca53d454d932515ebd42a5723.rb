class Scrabble
  attr_reader :word

  LETTER_SCORES = {
    %w(d g)       => 2,
    %w(b c m p)   => 3,
    %w(f h v w y) => 4,
    %w(k)         => 5,
    %w(j x)       => 8,
    %w(q z)       => 10
  }.each_with_object(Hash.new(1)) do |(letters, score), letter_scores|
    letters.each { |letter| letter_scores[letter] = score }
  end

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    word ||= ""
    @word = word.downcase.gsub(/[^a-z]/, "")
  end

  def score
    word.chars.reduce(0) { |score, letter| score + LETTER_SCORES[letter] }
  end
end
