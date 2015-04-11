class Scrabble
  VALUES = {}
  [ %w(A E I O U L N R S T), 1,
    %w(D G), 2,
    %w(B C M P), 3,
    %w(F H V W Y), 4,
    %w(K), 5,
    %w(J X), 6,
    %w(Q Z), 10 ].each_slice(2)
                 .each do |ary|
                    ary[0].map { |chr| VALUES[chr] = ary[1] }
                  end

  def initialize word
    @word = word
  end

  def score
    Scrabble.score @word
  end

  def self.score word
    return 0 if word.nil? || word.strip.empty?
    word.each_char.map { |chr| VALUES[chr.upcase] }.inject &:+
  end

end
