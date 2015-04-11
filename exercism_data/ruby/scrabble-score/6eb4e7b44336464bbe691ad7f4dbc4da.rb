class Scrabble
  def initialize(word)
    @word = word
  end

  def self.score(x)
    self.new(x).score
  end

  def score
    if @word.nil? || @word.empty?
      0
    else
      @word.downcase.each_char.map { |letter| points(letter) }.inject(&:+)
    end
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
