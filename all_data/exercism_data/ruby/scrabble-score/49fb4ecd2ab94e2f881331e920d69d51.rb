class Scrabble
  # An ugly first pass
  SCORES = Hash.new(0).tap do |scores|
    letter_values = <<-EOS
      A, E, I, O, U, L, N, R, S, T       1
      D, G                               2
      B, C, M, P                         3
      F, H, V, W, Y                      4
      K                                  5
      J, X                               8
      Q, Z                               10
    EOS

    letter_values.lines.each do |line|
      letters = line.scan(/[A-Z]/)
      score = line.match(/(\d+)/)[1].to_i
      letters.each { |letter| scores[letter] = score }
    end
  end

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word
  end

  def score
    word.chars.reduce(0) { |score, letter| score + SCORES[letter] }
  end

  def word
    @word.to_s.upcase
  end
end
