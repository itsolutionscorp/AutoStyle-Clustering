class Scrabble
  def initialize(word)
    @word = word
  end

  def self.score(w)
    self.new(w).score
  end

  def score
    return 0 if @word.nil? || @word.empty?
    @word.downcase.chars.inject(0){ |val, letter| val += points(letter) }
  end

  def points(letter)
    case letter
    when "a", "e", "i", "o", "u", "l", "n", "r", "s", "t"
      1
    when "d", "g"
      2
    when "b", "c", "m", "p"
      3
    when "f", "h", "v", "w", "y"
      4
    when "k"
      5
    when "j", "x"
      8
    when "q", "z"
      10
    else 
      0
    end
  end
end
