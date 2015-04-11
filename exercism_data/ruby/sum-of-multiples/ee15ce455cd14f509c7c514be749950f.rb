class SumOfMultiples
  def self.to(number)
    1.upto(number - 1).select { |integer| integer % 3 == 0 || integer % 5 == 0 }.reduce(0,:+)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(number)
    1.upto(number - 1).select do |integer|
     @multiples.any? { |m| integer % m == 0 } 
    end.reduce(0,:+)
  end
end
