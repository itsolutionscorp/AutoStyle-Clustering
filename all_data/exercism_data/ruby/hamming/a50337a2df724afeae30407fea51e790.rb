class Hamming

  def self.compute one, other
    h = Hamming.new one, other
    h.distance
  end

  attr_reader :one, :other

  def initialize one, other
    @one   = one
    @other = other
  end

  # [ ['X','X'], ['Z','X'], ... ]
  def distance 
    pairs.count { |(first, last)| first != last }
  end

  
  def pairs 
    one.chars.zip other.chars 
  end

end
