class Scrabble
  def initialize(word)
    @letters = word.to_s.strip.upcase.chars
    @total = 0
  end

  def score
    @letters.each do |l|
      SCORES.each_pair do |key, val|
        if val.include?(l)
          @total += key
        end
      end
    end
    @total
  end

  private

  def self.score(*args)
    new(args).score
  end

  SCORES = {
    1  => %w(A E I O U L N R S T),
    2  => %w(D G),
    3  => %w(B C M P),
    4  => %w(F H V W Y),
    5  => %w(K),
    8  => %w(J X),
    10 => %w(Q Z)
  }
end
