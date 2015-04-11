class Scrabble
  attr_reader :word
  def initialize(word)
    @word = word.to_s.strip
  end

  def score
    word.chars.map{ |char| Scrabble.letter_score char }.reduce(0, &:+)
  end

  def self.letter_score(letter)
    case letter.upcase.to_sym
    when :A, :E, :I, :O, :U, :L, :N, :R, :S, :T then 1
    when :D, :G                                 then 2
    when :B, :C, :M, :P                         then 3
    when :F, :H, :V, :W, :Y                     then 4
    when :K                                     then 5
    when :J, :X                                 then 8
    when :Q, :Z                                 then 10
    end
  end

  def self.score(word)
    new(word).score
  end
end
