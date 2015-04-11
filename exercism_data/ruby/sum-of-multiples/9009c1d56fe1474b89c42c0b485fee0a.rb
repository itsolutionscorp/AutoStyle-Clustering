class SumOfMultiples
  def initialize *params
    @multiples = params
  end
  
  def to number
    multiples = (0...number).select do |i|
      @multiples.detect{ |m| i % m == 0 }
    end

    multiples.inject(:+)
  end

  def self.to number
    SumOfMultiples.new(3, 5).to(number)
  end
end
