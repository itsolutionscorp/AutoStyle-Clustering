class Scrabble
  attr_reader :score
  def initialize word
    @score = Scrabble.score word
  end

  @@scores = {
    %W(A E I O U L N R S T) => 1,
    %W(D G)                 => 2,
    %W(B C M P)             => 3,
    %W(F H V W Y)           => 4,
    %W(K)                   => 5,
    %W(J X)                 => 8,
    %W(Q Z)                 => 10
  }

  def self.score word
    (word.nil? ? "" : word).upcase.delete("^A-Z").split("").reduce(0) {|total, letter| total += @@scores.find {|key, value| key.include? letter}[1]}
  end
end
