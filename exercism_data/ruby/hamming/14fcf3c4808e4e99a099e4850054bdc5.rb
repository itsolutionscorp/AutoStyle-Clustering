class Hamming

  attr_accessor :strand1, :strand2, :counter, :distance

  def self.compute strand1, strand2
    calculator = new strand1, strand2

    calculator.perform
  end

  def initialize strand1, strand2
    @strand1, @strand2  = strand1, strand2
    @counter, @distance = 0
  end

  def perform
    strand1.chars.each_with_index do |char, index|
      self.distance = calculate_distance char, index
    end
    distance
  end

private

  def calculate_distance char, index
    unless strand2[ index ].nil?
      self.counter += 1 if char != strand2[ index ]
    end
    
    counter
  end

end
