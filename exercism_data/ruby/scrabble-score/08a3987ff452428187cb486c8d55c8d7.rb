class Scrabble
  @@values = Hash.new(0)

  def self.letters_value(letters, value)
    letters.each { |letter| @@values[letter.to_s] = value }
  end

  letters_value [:A, :E, :I, :O, :U, :L, :N, :R, :S, :T], 1
  letters_value [:D, :G ],                                2
  letters_value [:B, :C, :M, :P],                         3
  letters_value [:F, :H, :V, :W, :Y],                     4
  letters_value [:K],                                     5
  letters_value [:J, :X],                                 8
  letters_value [:Q, :Z],                                 10

  def self.score(text)
    letters = normalize text
    letters.reduce(0) { |a, e| a + @@values[e] }
  end

  def self.normalize(text)
    text.to_s.chomp.upcase.chars
  end
  private_class_method :normalize


  def initialize(text)
    @text = text
  end

  def score
    self.class.score @text
  end
end
