class Hamming

  attr_accessor :strand1, :strand2, :distance

  def self.compute strand1, strand2
    calculator = new strand1, strand2

    calculator.perform
  end

  def initialize strand1, strand2
    @strand1, @strand2  = strand1, strand2
    @counter, @distance = 0
  end

  def perform
    (0...strand_size).count do |index|
      strand1[ index ] != strand2[ index ]
    end
  end

private

  def strand_size
    [ strand1.length, strand2.length ].min
  end
  
end
