class Scrabble

  SCORES = {
            %w(A E I O U L N R S T) => 1,
            %w(D G) =>                 2,
            %w(B C M P) =>             3,
            %w(F H V W Y) =>           4,
            %w(K) =>                   5,
            %w(J X) =>                 8,
            %w(Q Z) =>                 10}

  def initialize(str)
    @str = str || ""
  end

  def self.score(str)
    self.new(str).score
  end

  def score
    @str.upcase.gsub(/[^A-Za-z]/,"").split("").map do |letter|
      scores_hash[letter]
    end.reduce(:+) || 0
  end

  def scores_hash
    @scores ||=
      SCORES.inject({}) do |m,k|
      k[0].each do |letter|
        m[letter] = k[1]
      end
      m
    end
  end

end
